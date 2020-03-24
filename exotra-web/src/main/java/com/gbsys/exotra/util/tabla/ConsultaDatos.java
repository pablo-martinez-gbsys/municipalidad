package com.gbsys.exotra.util.tabla;

import com.gbsys.exotra.entidad.EntidadBase;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Interfaz que define como debe ser el método que obtiene los datos de una
 * TablaControlLazy.
 *
 * @param <E> Elemento genérico que extiende de EntidadBase
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 16/05/2018 14:30
 */
@FunctionalInterface
public interface ConsultaDatos<E extends EntidadBase> extends Serializable {

    /**
     * Obtiene los datos de una TablaControlLazy.
     *
     * @param inicio Registro inicial de la BD
     * @param tamanoPagina Cantidad de registros de la BD
     * @param ordenamiento Ordenamiento que debe tener la consulta a la BD
     * @param filtros Filtro a ser aplicados a la consulta a la BD
     * @return Lista de elementos cargados
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 16/05/2018 14:30
     */
    List<E> consultar(int inicio, int tamanoPagina, String ordenamiento, Map<String, Object> filtros);
}
