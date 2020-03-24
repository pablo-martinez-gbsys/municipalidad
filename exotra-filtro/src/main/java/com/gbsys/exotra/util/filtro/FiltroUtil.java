package com.gbsys.exotra.util.filtro;

import com.gbsys.exotra.exception.FiltroException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Utilitario encargada de facilitar la creación de ConsultaParametros.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 15/05/2018 16:31
 */
@Slf4j
@UtilityClass
public class FiltroUtil {

    /**
     * Funciona como cache de las condiciones, para no tener que instanciarlas
     * con reflection en cada ocación lo cual es lento.
     */
    private static final Map<Class<? extends Condicion>, Condicion> CONDICIONES = new ConcurrentHashMap<>();

    /**
     * Carga inicial de las condiciones comunes.
     */
    static {
        //SQL
        CONDICIONES.put(CondicionIgual.class, new CondicionIgual());
        CONDICIONES.put(CondicionDiferente.class, new CondicionDiferente());
        CONDICIONES.put(CondicionMayor.class, new CondicionMayor());
        CONDICIONES.put(CondicionMayorIgual.class, new CondicionMayorIgual());
        CONDICIONES.put(CondicionMenor.class, new CondicionMenor());
        CONDICIONES.put(CondicionMenorIgual.class, new CondicionMenorIgual());
        CONDICIONES.put(CondicionContiene.class, new CondicionContiene());
        CONDICIONES.put(CondicionIniciaCon.class, new CondicionIniciaCon());
        CONDICIONES.put(CondicionTerminaCon.class, new CondicionTerminaCon());
        CONDICIONES.put(CondicionEn.class, new CondicionEn());
        CONDICIONES.put(CondicionNoEn.class, new CondicionNoEn());
        CONDICIONES.put(CondicionEsNulo.class, new CondicionEsNulo());
        CONDICIONES.put(CondicionNoEsNulo.class, new CondicionNoEsNulo());
        //Especiales
        CONDICIONES.put(CondicionConstante.class, new CondicionConstante());
        CONDICIONES.put(CondicionLiteral.class, new CondicionLiteral());
        CONDICIONES.put(CondicionMapStringObject.class, new CondicionMapStringObject());
        CONDICIONES.put(CondicionLiteralMapStringObject.class, new CondicionLiteralMapStringObject());
    }

    /**
     * Obtiene del objeto Filtrable los atributos anotados con Filtro y con
     * valor diferente de null, luego los agrega a la consulta original y guarda
     * los valores en un mapa y los retorna en un objeto ConsultaParametros para
     * la búsqueda con filtro.
     *
     * @param filtrable Objeto Filtrable
     * @return Objeto con la consulta y parámetros
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 15/05/2018 16:31
     */
    public static ConsultaParametros obtenerConsultaParametros(Filtrable filtrable) {
        try {
            StringBuilder consulta = new StringBuilder(filtrable.consulta());
            if (filtrable.incluirWhere()) {
                consulta.append(Filtrable.WHERE_INICIAL);
            }
            Map<String, Object> parametros = new HashMap<>();
            List<Field> fields = consultaCampos(filtrable.getClass());
            for (Field field : fields) {
                Filtro filtro = field.getAnnotation(Filtro.class);
                if (filtro == null) {
                    continue;
                }
                field.setAccessible(true);
                Object obj = field.get(filtrable);
                if (esNuloVacio(obj)) {
                    continue;
                }
                if (filtro.ignorarCero() && esNumeroCero(obj)) {
                    continue;
                }
                if (filtro.ignorarFalso() && esBooleanFalso(obj)) {
                    continue;
                }

                Condicion condicion = obtenerCondicion(filtro.condicion());
                condicion.aplicar(consulta, parametros, filtro.value(), field.getName(), obj);
            }
            if (filtrable.incluirGroupBy() && !esNuloVacio(filtrable.agrupacion())) {
                consulta.append(Filtrable.GROUP_BY);
                consulta.append(filtrable.agrupacion());
            }
            if (filtrable.incluirOrderBy() && !esNuloVacio(filtrable.ordenamiento())) {
                consulta.append(Filtrable.ORDER_BY);
                consulta.append(filtrable.ordenamiento());
            }
            return new ConsultaParametros(consulta.toString(), parametros);
        } catch (Exception ex) {
            log.error("FiltroUtil.obtenerConsultaParametros({})", filtrable, ex);
            throw new FiltroException("Error al crear la consulta filtrada", ex);
        }
    }

    /**
     * Consulta todos los campos de una clase, incluso los heredados.
     *
     * @param tipo Clase a consultar los campos
     * @return Lista de campos de la clase, incluidos los heredados.
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 25/05/2018 07:31
     */
    private static List<Field> consultaCampos(Class<?> tipo) {
        List<Field> campos = new ArrayList<>();
        for (Class<?> clase = tipo; clase != null; clase = clase.getSuperclass()) {
            campos.addAll(Arrays.asList(clase.getDeclaredFields()));
        }
        return campos;
    }

    /**
     * Obtiene la instancia de la implementación de Condicion del cache, si no
     * existe en el cache crea una nueva instancia con reflection y la guarda en
     * el cache para el futuro.
     *
     * @param claseCondicion Clase que implementa Condicion
     * @return Instancia de la clase
     * @throws Exception Si ocurre algún error creando la instancia, por ejemplo
     * que la implementación no tenga constructor vacío.
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 15/05/2018 16:31
     */
    private static Condicion obtenerCondicion(Class<? extends Condicion> claseCondicion) throws Exception {
        if (CONDICIONES.containsKey(claseCondicion)) {
            return CONDICIONES.get(claseCondicion);
        } else {
            Condicion condicion = claseCondicion.newInstance();
            CONDICIONES.put(claseCondicion, condicion);
            return condicion;
        }
    }

    /**
     * Verifica si un objeto es nulo o String vacío o Collection vacío o Map
     * vacío.
     *
     * @param obj Objeto a verificar
     * @return true si es nulo o vacío, false en caso contrario
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 15/05/2018 16:31
     */
    private boolean esNuloVacio(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String) {
            String objStr = (String) obj;
            return objStr.trim().isEmpty();
        } else if (obj instanceof Collection) {
            Collection objCol = (Collection) obj;
            return objCol.isEmpty();
        } else if (obj instanceof Map) {
            Map objMap = (Map) obj;
            return objMap.isEmpty();
        } else {
            return false;
        }
    }

    /**
     * Verifica si el objeto es de tipo numérico y si tiene valor de cero.
     *
     * @param valor Objeto a verificar
     * @return true si es numérico y es cero, false en caso contrario
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 15/05/2018 16:31
     */
    private static boolean esNumeroCero(Object valor) {
        if (valor instanceof Number) {
            if (valor instanceof Long) {
                return Objects.equals(valor, 0L);
            }
            if (valor instanceof Double) {
                return Objects.equals(valor, 0D);
            }
            if (valor instanceof Integer) {
                return Objects.equals(valor, 0);
            }
            if (valor instanceof BigDecimal) {
                return Objects.equals(valor, BigDecimal.ZERO);
            }
            if (valor instanceof BigInteger) {
                return Objects.equals(valor, BigInteger.ZERO);
            } else {
                return false;//Si no es de los tipos soportados se asume falso
            }
        } else {
            return false;
        }
    }

    /**
     * Verifica si el objeto es de tipo booleano y si tiene valor de falso.
     *
     * @param valor Objeto a verificar
     * @return true si es booleano y es falso, false en caso contrario
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 15/05/2018 16:31
     */
    private static boolean esBooleanFalso(Object valor) {
        if (valor instanceof Boolean) {
            return Objects.equals(Boolean.FALSE, valor);
        } else {
            return false;
        }
    }
}
