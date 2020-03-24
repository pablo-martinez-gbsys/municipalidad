package com.gbsys.exotra.util.filtro;

import java.io.Serializable;

/**
 * Interfaz encargada de indicar si un objeto se puede filtrar.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 29/11/2017 16:51
 */
public interface Filtrable extends Serializable {

    /**
     * Where inicial, donde 1=1.
     */
    String WHERE_INICIAL = " WHERE 1=1";
    /**
     * Ordenamiento.
     */
    String ORDER_BY = " ORDER BY ";
    /**
     * Ordenamiento.
     */
    String GROUP_BY = " GROUP BY ";

    /**
     * Consulta a realizar.
     *
     * @return consulta a realizar
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 29/11/2017 16:51
     */
    String consulta();

    /**
     * Ordenamiento de la consulta.
     *
     * @return ordenamiento de la consulta
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 29/11/2017 16:51
     */
    String ordenamiento();
    /**
     * A de la consulta.
     *
     * @return ordenamiento de la consulta
     * @author GBSYS. Diseñado y Desarrollado por: Gustavo Martinez 
     * @version 1.0.0
     * @date 29/11/2017 16:51
     */
    String agrupacion();

    /**
     * Tipo de consulta.
     *
     * @return tipo de consulta
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 29/11/2017 16:51
     */
    TipoConsulta tipoConsulta();

    /**
     * Indica si se debe incluir el WHERE en la consulta.
     *
     * @return true si se debe incluir, false en caso contrario
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 15/05/2018 16:51
     */
    boolean incluirWhere();

    /**
     * Indica si se debe incluir el ORDER BY en la consulta.
     *
     * @return true si se debe incluir, false en caso contrario
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 15/05/2018 16:51
     */
    boolean incluirOrderBy();
    /**
     * Indica si se debe incluir el group by en la consulta.
     *
     * @return true si se debe incluir, false en caso contrario
     * @author GBSYS. Diseñado y Desarrollado por: Gustavo Martinez 
     * @version 1.0.0
     * @date 15/05/2018 16:51
     */
    boolean incluirGroupBy();
}
