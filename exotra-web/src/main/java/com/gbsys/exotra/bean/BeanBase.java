package com.gbsys.exotra.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;

/**
 * ManagedBean Base encargado de cumplir la función de heredar características
 * comunes a los demás ManagedBeans.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 14/11/2017 17:23
 */
public abstract class BeanBase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Inicializa la variables del managed bean.
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 14/11/2017 17:23
     */
    abstract void inicializar();

    /**
     * Inicializa las variables del managed bean luego que los servicios sean
     * inyectados por CDI.
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 14/11/2017 17:23
     */
    abstract void inicializarPost();

    /**
     * Muestra un mensaje de información.
     *
     * @param etiqueta etiqueta del mensaje
     * @param argumentos argumentos del mensaje
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 10/12/2017 18:53
     */
    protected void mensajeInformacion(String etiqueta, Object... argumentos) {
        Messages.addGlobalInfo(etiqueta, argumentos);
    }

    /**
     * Muestra un mensaje de advertencia.
     *
     * @param etiqueta etiqueta del mensaje
     * @param argumentos argumentos del mensaje
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 10/12/2017 18:53
     */
    protected void mensajeAdvertencia(String etiqueta, Object... argumentos) {
        Messages.addGlobalWarn(etiqueta, argumentos);
    }

    /**
     * Muestra un mensaje de error.
     *
     * @param etiqueta etiqueta del mensaje
     * @param argumentos argumentos del mensaje
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 10/12/2017 18:53
     */
    protected void mensajeError(String etiqueta, Object... argumentos) {
        Messages.addGlobalError(etiqueta, argumentos);
    }

    /**
     * Limpia los filtros de una o mas tablas.
     *
     * @param tablas Lista de tablas a limpiar los filtros
     * @version 1.0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing.Ing. Herman Barrantes
     * @date 20/03/2017 08:14
     */
    protected void limpiarFiltro(String... tablas) {
        List<String> componentes = new ArrayList<>();
        for (String nombreTabla : tablas) {
            DataTable tabla = (DataTable) FacesContext
                    .getCurrentInstance()
                    .getViewRoot()
                    .findComponent(nombreTabla);
            if (tabla != null && !tabla.getFilters().isEmpty()) {
                tabla.reset();
                tabla.setSortBy(null);
                componentes.add(nombreTabla);
            }
        }
        if (!componentes.isEmpty()) {
            PrimeFaces.current().ajax().update(componentes);
        }
    }
}
