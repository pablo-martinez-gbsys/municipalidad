package com.gbsys.exotra.util.reporte;

import com.gbsys.exotra.exception.UtilException;
import com.gbsys.exotra.util.Formato;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleRtfReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

/**
 * Servicio encargado de generar los reportes del sistema en sus diferentes
 * formatos.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes.
 * @version 1.0.0
 * @date 24/03/2017 11:30
 */
@Slf4j
@Stateless
@SuppressWarnings("unchecked")
public class ServicioReporte {

    @Inject
    private ServletContext context;
    @Inject
    private ConexionUtil conexionUtil;

    /**
     * Genera el arreglo de bytes del reporte especificado, en el formato
     * indicado.
     *
     * @param reporte Enumerado del reporte a generar
     * @param formato Formato de salida del reporte
     * @param parametros Parámetros requeridos por el reporte
     * @return Arreglo de bytes del reporte generado
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes.
     * @version 1.0.0
     * @date 24/03/2017 11:30
     */
    public byte[] generarReporte(Reporte reporte, Formato formato, Map<String, Object> parametros) {
        try (Connection conn = conexionUtil.obtenerDataSource().getConnection()) {

            String realPath = context.getRealPath("/WEB-INF/reporte") + File.separator;
            parametros.put("RUTA_REPORTES", realPath);
            if (!parametros.containsKey("NOMBRE_REPORTE")) {
                parametros.put("NOMBRE_REPORTE", reporte.getNombre());
            }
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(realPath + reporte.getRuta());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);

            return exportar(jasperPrint, formato);
        } catch (JRRuntimeException | JRException | SQLException ex) {
            log.error("ServicioReporte.generarReporte({}, {}, {})", reporte, formato, parametros, ex);
            throw new UtilException("Ocurrió un error al generar el reporte", ex);
        }
    }

    /**
     * Genera el arreglo de bytes del reporte especificado, en el formato
     * indicado.
     *
     * @param reporte Enumerado del reporte a generar
     * @param formato Formato de salida del reporte
     * @param parametros Parámetros requeridos por el reporte
     * @param coleccion Colección de objetos para imprimir el reporte
     * @return Arreglo de bytes del reporte generado
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes.
     * @version 1.0.0
     * @date 24/03/2017 11:30
     */
    public byte[] generarReporte(Reporte reporte, Formato formato, Map<String, Object> parametros, Collection coleccion) {
        try {

            String realPath = context.getRealPath("/WEB-INF/reporte") + File.separator;
            parametros.put("RUTA_REPORTES", realPath);
            if (!parametros.containsKey("NOMBRE_REPORTE")) {
                parametros.put("NOMBRE_REPORTE", reporte.getNombre());
            }
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(realPath + reporte.getRuta());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JRBeanCollectionDataSource(coleccion));

            return exportar(jasperPrint, formato);
        } catch (JRRuntimeException | JRException ex) {
            log.error("ServicioReporte.generarReporte({}, {}, {}, {})", reporte, formato, parametros, coleccion, ex);
            throw new UtilException("Ocurrió un error al generar el reporte", ex);
        }
    }

    /**
     * Exporta el reporte en el formato especificado.
     *
     * @param jp Objeto JasperPrint del reporte a generar
     * @param formato Formato de salida del reporte
     * @return Arreglo de bytes del reporte generado
     * @throws JRException En caso de error en JasperReport
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes.
     * @version 1.0.0
     * @date 24/03/2017 11:30
     */
    private byte[] exportar(JasperPrint jp, Formato formato) throws JRException {

        Exporter exporter = crearExporter(formato);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        exporter.setExporterInput(new SimpleExporterInput(jp));
        exporter.setExporterOutput(crearExporterOutput(os, formato));
        exporter.exportReport();

        return os.toByteArray();
    }

    /**
     * Genera el ExporterOutput requerido para cada formato.
     *
     * @param os OutputStream del reporte
     * @param formato Formato de salida del reporte
     * @return ExporterOutput requerido para cada formato
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes.
     * @version 1.0.0
     * @date 20/07/2018 12:00
     */
    private ExporterOutput crearExporterOutput(OutputStream os, Formato formato) {
        switch (formato) {
            case RTF:
            case CSV:
                return new SimpleWriterExporterOutput(os);
            case PDF:
            case XLSX:
            case DOCX:
            case HTML:
            default:
                return new SimpleOutputStreamExporterOutput(os);
        }
    }

    /**
     * Genera el exporte para el formato especificado
     *
     * @param formato Formato de salida del reporte
     * @return Retorna el objeto Exporter para el formato especificado
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes.
     * @version 1.0.0
     * @date 24/03/2017 11:30
     */
    private Exporter crearExporter(Formato formato) {
        Exporter exporter = null;

        switch (formato) {
            case PDF: {
                exporter = new JRPdfExporter();
                SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
                exporter.setConfiguration(configuration);
                break;
            }
            case XLSX: {
                exporter = new JRXlsxExporter();
                SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                configuration.setDetectCellType(Boolean.TRUE);
                configuration.setOnePagePerSheet(Boolean.FALSE);
                configuration.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                configuration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                configuration.setWhitePageBackground(Boolean.TRUE);
                configuration.setIgnoreCellBorder(Boolean.TRUE);
                configuration.setIgnorePageMargins(Boolean.TRUE);
                configuration.setSheetNames(new String[]{"REPORTE"});
                exporter.setConfiguration(configuration);
                break;
            }
            case DOCX: {
                exporter = new JRDocxExporter();
                SimpleDocxReportConfiguration configuration = new SimpleDocxReportConfiguration();
                exporter.setConfiguration(configuration);
                break;
            }
            case RTF: {
                exporter = new JRRtfExporter();
                SimpleRtfReportConfiguration configuration = new SimpleRtfReportConfiguration();
                exporter.setConfiguration(configuration);
                break;
            }
            case HTML: {
                exporter = new HtmlExporter();
                SimpleHtmlExporterConfiguration configuration = new SimpleHtmlExporterConfiguration();
                exporter.setConfiguration(configuration);
                break;
            }
            case CSV: {
                exporter = new JRCsvExporter();
                SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
                exporter.setConfiguration(configuration);
                break;
            }
            default:
                throw new UtilException("Formato de reporte no soportado");
        }

        return exporter;
    }

}
