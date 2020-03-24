package com.gbsys.exotra.servicio;

import com.gbsys.exotra.entidad.EntidadBase;
import com.gbsys.exotra.exception.ServicioException;
import com.gbsys.exotra.util.filtro.ConsultaParametros;
import com.gbsys.exotra.util.filtro.Filtrable;
import com.gbsys.exotra.util.filtro.FiltroUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;

/**
 * Servicio Base encargado de cumplir la función de heredar características
 * comunes a los demás servicios.
 *
 * @param <E> Clase genérica del servicio
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 13/11/2017 17:08
 */
@Slf4j
public abstract class ServicioBase<E extends EntidadBase> {

    @PersistenceContext(unitName = "EXOTRA")
    protected EntityManager em;

    protected JPAQueryFactory qf;

    @PostConstruct
    private void init() {
        qf = new JPAQueryFactory(em);
    }

    /**
     * Nombre del método que se encarga de obtener el String del CLOB.
     */
    private static final String GET_WRAPPED_CLOB = "getWrappedClob";

    /**
     * Valor numérico para representar true.
     */
    private static final int VALOR_TRUE = 1;

    /**
     * Aplica los parámetros a la consulta.
     *
     * @param query Consulta donde se debe aplicar los parámetros
     * @param parametros Mapa de parámetros a aplicar
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 29/11/2017 16:54
     */
    private void aplicarParametros(Query query, Map<String, Object> parametros) {
        try {
            for (Map.Entry<String, Object> parametro : parametros.entrySet()) {
                query.setParameter(parametro.getKey(), parametro.getValue());
            }
        } catch (Exception ex) {
            log.error("ServicioBase.aplicarParametros(QUERY, {})", parametros, ex);
            throw new ServicioException("mensaje.error.servicio_base.aplicarParametros", ex);
        }
    }

    /**
     * Realiza la búsqueda de todas las filas que concuerden con los atributos
     * no nulos del filtro.
     *
     * @param filtrable Ejemplo para los criterios de búsqueda
     * @return Lista de entidades que concuerdan con los criterios de búsqueda.
     * @throws ServicioException En caso de error al consultar
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 29/11/2017 16:54
     */
    protected List consultarFiltro(Filtrable filtrable) {
        try {
            ConsultaParametros consultaParametros = FiltroUtil.obtenerConsultaParametros(filtrable);
            Query query = null;
            switch (filtrable.tipoConsulta()) {
                case JPQL:
                    query = em.createQuery(consultaParametros.getConsulta());
                    break;
                case NATIVA:
                    query = em.createNativeQuery(consultaParametros.getConsulta());
                    break;
                default:
                    query = em.createQuery(consultaParametros.getConsulta());
                    break;
            }
            aplicarParametros(query, consultaParametros.getParametros());
            return query.getResultList();
        } catch (Exception ex) {
            log.error("ServicioBase.consultarFiltro({})", filtrable, ex);
            throw new ServicioException("mensaje.error.servicio_base.consultarFiltro", ex);
        }
    }

    /**
     * Realiza la búsqueda de todas las filas que concuerden con los atributos
     * no nulos del filtro y realiza la paginación en la consulta.
     *
     * @param filtrable Ejemplo para los criterios de búsqueda
     * @param inicio Posición del primer registro de la consulta
     * @param cantidad Cantidad de datos de la consulta
     * @return Lista de entidades que concuerdan con los criterios de búsqueda.
     * @throws ServicioException En caso de error al consultar
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 24/05/2018 07:54
     */
    protected List consultarFiltro(Filtrable filtrable, int inicio, int cantidad) {
        try {
            ConsultaParametros consultaParametros = FiltroUtil.obtenerConsultaParametros(filtrable);
            Query query = null;
            switch (filtrable.tipoConsulta()) {
                case JPQL:
                    query = em.createQuery(consultaParametros.getConsulta());
                    break;
                case NATIVA:
                    query = em.createNativeQuery(consultaParametros.getConsulta());
                    break;
                default:
                    query = em.createQuery(consultaParametros.getConsulta());
                    break;
            }
            aplicarParametros(query, consultaParametros.getParametros());
            return query
                    .setFirstResult(inicio)
                    .setMaxResults(cantidad)
                    .getResultList();
        } catch (Exception ex) {
            log.error("ServicioBase.consultarFiltro({}, {}, {})", filtrable, inicio, cantidad, ex);
            throw new ServicioException("mensaje.error.servicio_base.consultarFiltro", ex);
        }
    }

    /**
     * Guarda un registro en la base de datos.
     *
     * @param obj Objeto a guardar
     * @throws ServicioException En caso de error al guardar
     */
    protected E guardar(E obj) {
        try {
            em.persist(obj);
            em.flush();
            return obj;
        } catch (Exception ex) {
            log.error("ServicioBase.guardar({})", obj.toString(), ex);
            throw new ServicioException("mensaje.error.servicio_base.guardar", ex);
        }
    }

    /**
     * Actualiza un registro existente en la base datos.
     *
     * @param obj Objeto a actualizar
     * @throws ServicioException En caso de error al actualizar
     */
    protected E actualizar(E obj) {
        try {
            em.merge(obj);
            em.flush();
            return obj;
        } catch (Exception ex) {
            log.error("ServicioBase.actualizar({})", obj.toString(), ex);
            throw new ServicioException("mensaje.error.servicio_base.actualizar", ex);
        }
    }

    /**
     * Elimina un registro existente en la base datos.
     *
     * @param obj Objeto a eliminar
     * @throws ServicioException En caso de error al eliminar
     */
    protected void eliminar(E obj) {
        try {
            em.remove(em.merge(obj));
            em.flush();
        } catch (Exception ex) {
            log.error("ServicioBase.eliminar({})", obj.toString(), ex);
            throw new ServicioException("mensaje.error.servicio_base.eliminar", ex);
        }
    }

    /**
     * Obtiene un registro de la base de datos por su llave primaria.
     *
     * @param clase Clase a encontrar
     * @param pk Llave primaria del objeto a encontrar
     * @return Objeto encontrado
     * @throws ServicioException En caso de error al encontrar
     */
    protected E obtener(Class<E> clase, Serializable pk) {
        try {
            return em.find(clase, pk);
        } catch (Exception ex) {
            log.error("ServicioBase.encontrar({}, {})", clase.getSimpleName(), pk, ex);
            throw new ServicioException("mensaje.error.servicio_base.obtener", ex);
        }
    }

    /**
     * Consulta todos los registros de la base de datos.
     *
     * @param clase Clase a consultar
     * @return Lista de objetos encontrados
     * @throws ServicioException En caso de error al listar
     */
    protected List<E> consultarTodos(Class<E> clase) {
        try {
            String consulta = "select obj from " + clase.getSimpleName() + " obj";
            TypedQuery<E> query = em.createQuery(consulta, clase);
            return query.getResultList();
        } catch (Exception ex) {
            log.error("ServicioBase.listar({})", clase.getSimpleName(), ex);
            throw new ServicioException("mensaje.error.servicio_base.consultarTodos", ex);
        }
    }

    /**
     * Convierte un valor de una consulta nativa a Integer.
     *
     * @param obj Objeto a convertir
     * @return Valor del Integer o null
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 14/03/2017 15:10
     */
    protected Integer aInteger(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        return null;
    }

    /**
     * Convierte un valor de una consulta nativa a Long.
     *
     * @param obj Objeto a convertir
     * @return Valor del Long o null
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 14/03/2017 15:10
     */
    protected Long aLong(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        return null;
    }

    /**
     * Convierte un valor de una consulta nativa a Doble.
     *
     * @param obj Objeto a convertir
     * @return Valor del Double o null
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 10/04/2017 14:22
     */
    protected Double aDouble(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        return null;
    }

    /**
     * Convierte un valor de una consulta nativa a String.
     *
     * @param obj Objeto a convertir
     * @return Valor del String o null
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 14/03/2017 15:10
     */
    protected String aString(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof Character) {
            return String.valueOf((Character) obj);
        }
        return null;
    }

    /**
     * Convierte un valor de una consulta nativa a Date.
     *
     * @param obj Objeto a convertir
     * @return Valor del Date o null
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 14/03/2017 15:10
     */
    protected Date aDate(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Date) {
            return (Date) obj;
        }
        return null;
    }

    /**
     * Convierte un valor de una consulta nativa a Integer.
     *
     * @param obj Objeto a convertir
     * @return Valor del Integer o null
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 14/03/2017 15:10
     */
    protected Boolean aBoolean(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return ((Number) obj).intValue() == VALOR_TRUE;
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        return null;
    }

    /**
     * Convierte un String de una consulta nativa a un Enumerado.
     *
     * @param <T> Tipo genérico de enumerado
     * @param nombre Valor como String del enumerado
     * @param enumerado Clase del enumerado
     * @param defecto valor por defecto si no lo encuentra
     * @return Valor del String a Enumerado o el valor por defecto si no lo
     * encuentra
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 21/04/2017 07:50
     */
    protected <T extends Enum<T>> T aEnum(String nombre, Class<T> enumerado, T defecto) {
        if (nombre == null || enumerado == null) {
            return defecto;
        }
        for (T valor : enumerado.getEnumConstants()) {
            if (valor.name().equals(nombre)) {
                return valor;
            }
        }
        return defecto;
    }

    /**
     * Convierte un String de una consulta nativa a un Enumerado.
     *
     * @param <T> Tipo genérico de enumerado
     * @param nombre Valor como String del enumerado
     * @param enumerado Clase del enumerado
     * @return Valor del String a Enumerado o null si no lo encuentra
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 21/04/2017 07:50
     */
    protected <T extends Enum<T>> T aEnum(String nombre, Class<T> enumerado) {
        return aEnum(nombre, enumerado, null);
    }

    /**
     * Convierte un proxy de CLOB de una consulta nativa a un String.
     *
     * @param proxy Objecto proxy
     * @return El valor del CLOB como String
     * @throws ServicioException En caso de error al obtener el valor del CLOB
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 21/06/2017 07:50
     */
    protected String aStringClob(Object proxy) {
        if (proxy == null) {
            return null;
        }
        if(proxy instanceof String || proxy instanceof Character) {
            return aString(proxy);
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(proxy.getClass());
            for (PropertyDescriptor property : beanInfo.getPropertyDescriptors()) {
                Method readMethod = property.getReadMethod();
                if (readMethod.getName().equals(GET_WRAPPED_CLOB)) {
                    Object result = readMethod.invoke(proxy);
                    return clobToString((Clob) result);
                }
            }
        } catch (Exception ex) {
            log.error("ServicioBase.aStringClob(PROXY)", ex);
            throw new ServicioException("mensaje.error.servicio_base.aStringClob", ex);
        }
        return null;
    }

    /**
     * Convierte un objeto CLOB a String.
     *
     * @param data El CLOB a convertir
     * @return El valor del CLOB como String
     * @throws SQLException En caso de error en la consulta de SQL.
     * @throws IOException En caso de error de IO.
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 21/06/2017 07:50
     */
    private String clobToString(Clob data) throws SQLException, IOException {
        if (data == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Reader reader = data.getCharacterStream();
        try (BufferedReader br = new BufferedReader(reader)) {
            String line;
            while (null != (line = br.readLine())) {
                sb.append(line);
            }
        }
        return sb.toString();
    }

}
