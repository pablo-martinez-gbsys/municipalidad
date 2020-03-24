package com.gbsys.exotra.util.reporte;

import com.gbsys.exotra.exception.UtilException;
import javax.ejb.Singleton;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;

/**
 * Utilitario encargado de obtener los orígenes de datos.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 12/07/2017 11:25
 */
@Slf4j
@Singleton
public class ConexionUtil {

    /**
     * Nombre del JNDI donde se encuentra el origen de datos.
     */
    private static final String JNDI = "jdbc/exotra";

    /**
     * Poll de conexiones del origen de datos.
     */
    private DataSource dataSource;

    /**
     * Obtiene el DataSource para el JNDI de fiduciaria.
     *
     * @return DataSource de fiduciaria.
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.2
     * @date 12/07/2017 11:25
     */
    public DataSource obtenerDataSource() {
        if (dataSource == null) {
            dataSource = crearDataSource();
        }
        return dataSource;
    }

    /**
     * Crea un DataSource para el JNDI de fiduciaria.
     *
     * @return DataSource de fiduciaria.
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.2
     * @date 12/07/2017 11:25
     */
    private DataSource crearDataSource() {
        try {
            Context initialContext = new InitialContext();
            return (DataSource) initialContext.lookup(JNDI);
        } catch (NamingException ex) {
            log.error("ConexionUtil.crearDataSource()", ex);
            throw new UtilException("No se pudo crear la conexión a la base de datos", ex);
        }
    }
}
