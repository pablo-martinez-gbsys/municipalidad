package com.gbsys.exotra.util.filtro;

/**
 * Filtro Base encargado de cumplir la función de heredar características
 * comunes a los demás filtros.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 30/11/2017 07:28
 */
public abstract class FiltroBase implements Filtrable {

    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    @Override
    public TipoConsulta tipoConsulta() {
        return TipoConsulta.JPQL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String ordenamiento() {
        return "";
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String agrupacion() {
        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean incluirWhere() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean incluirOrderBy() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean incluirGroupBy() {
        return false;
    }
}
