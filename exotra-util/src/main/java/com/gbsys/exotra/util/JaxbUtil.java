package com.gbsys.exotra.util;

import com.gbsys.exotra.exception.UtilException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Utilitario para trabajar XML con JAX-B.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 21/08/2017 10:48
 */
@Slf4j
@UtilityClass
public class JaxbUtil {

    private static final String MSG_ERROR_A_STRING
            = "Ocurrió un error al convertir el objeto a xml.";
    private static final String MSG_ERROR_A_OBJECTO
            = "Ocurrió un error al convertir el xml a objeto.";

    /**
     * Convierte uno o mas objetos a XML y los retorna como un String.
     *
     * @param obj Objeto(s) a convertir
     * @param clases Clase(s) del objeto a convertir.
     * @return XML como String
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 21/08/2017 10:48
     */
    public static String aString(Object obj, Class... clases) {
        try {
            StringWriter stringWriter = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(clases);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            jaxbMarshaller.marshal(obj, stringWriter);
            return stringWriter.toString();
        } catch (JAXBException ex) {
            log.error(MSG_ERROR_A_STRING, ex);
            throw new UtilException(MSG_ERROR_A_OBJECTO);
        }
    }

    /**
     * Convierte un String que contiene un XML a uno o mas Objetos.
     *
     * @param <T> Tipo genérico
     * @param xml String que contiene el XML
     * @param clase Clase del objeto a convertir.
     * @return Objeto(s) del XML
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 21/08/2017 10:48
     */
    public static <T> T aObjeto(String xml, Class<T> clase) {
        try {
            String xmlCorregido = corregirEncoding(xml.trim());
            StringReader stringReader = new StringReader(xmlCorregido);
            JAXBContext jaxbContext = JAXBContext.newInstance(clase);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return clase.cast(jaxbUnmarshaller.unmarshal(stringReader));
        } catch (JAXBException ex) {
            log.error(MSG_ERROR_A_OBJECTO, ex);
            throw new UtilException(MSG_ERROR_A_OBJECTO);
        }
    }

    /**
     * Método para pasar de UTF-8 con BOM a UTF-8 sin BOM.
     *
     * @param entrada texto a remover el BOM
     * @return texto en UTF-8 sin BOM
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 21/08/2017 10:48
     */
    private static String corregirEncoding(String entrada) {
        if (entrada.startsWith("\uFEFF")) {//UTF-18 BOM
            return entrada.replace("\uFEFF", "");
        } else if (entrada.startsWith("\uFFFD")) {//UTF-16 LE
            return entrada.replace("\uFFFD", "").replace("\u0000", "");
        } else {
            return entrada;
        }
    }
}
