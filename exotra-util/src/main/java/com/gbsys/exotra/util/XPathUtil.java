package com.gbsys.exotra.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Utilitario para trabajar XML con XPath.
 * http://docs.oracle.com/javase/tutorial/jaxp/xslt/xpath.html
 * http://viralpatel.net/blogs/java-xml-xpath-tutorial-parse-xml/
 *
 * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 23/09/2014
 */
@Slf4j
public class XPathUtil {

    /**
     * Obtiene el valor del atributo de un elemento.
     *
     * @param elemento Elemento a extrar el valor del atributo.
     * @param atributo Nombre del atributo.
     * @return Valor del atributo.
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 23/09/2014
     */
    public static String obtenerAtributoElemento(Element elemento, String atributo) {
        return elemento.getAttribute(atributo);
    }

    /**
     * Obtiene el valor del elemento.
     *
     * @param elemento Elemento a extrar el valor.
     * @return Valor del elemento o null si no contiene.
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 23/09/2014
     */
    public static String obtenerValorElemento(Element elemento) {
        if (elemento.hasChildNodes()) {
            return elemento.getFirstChild().getTextContent();
        } else {
            return null;
        }
    }

    /**
     * Obtiene el valor de una etiqueta del elemento.
     *
     * @param elemento Elemento a extrar el valor de la etiqueta.
     * @param etiqueta Nombre de la etiqueta.
     * @return Valor de la etiqueta o null si no contiene la etiqueta.
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 23/09/2014
     */
    public static String obtenerValorElemento(Element elemento, String etiqueta) {
        NodeList elementos = elemento.getChildNodes();
        for (int i = 0; i < elementos.getLength(); i++) {
            Node nodo = elementos.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE && nodo.getNodeName().equals(etiqueta)) {
                return obtenerValorElemento((Element) nodo);
            }
        }
        return null;
    }

    /**
     * Obtiene el documento XML a partir de un String.
     *
     * @param documento String con XML.
     * @return Documento con XML.
     * @throws ParserConfigurationException Error al leer el XML.
     * @throws SAXException Error al leer el XML.
     * @throws IOException Error al leer el String.
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 23/09/2014
     */
    private static Document obtenerDocumento(String documento) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new InputSource(new StringReader(documento.trim())));
//        Document xmlDocument = builder.parse(new ByteArrayInputStream(documento.trim().getBytes()));
        return xmlDocument;
    }

    /**
     * Obtiene el documento XML a partir de un String.
     *
     * @param documento String con XML.
     * @return Documento con XML.
     * @throws ParserConfigurationException Error al leer el XML.
     * @throws SAXException Error al leer el XML.
     * @throws IOException Error al leer el String.
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 23/09/2014
     */
    private static Document obtenerDocumento(InputStream documento) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(documento);
        return xmlDocument;
    }

    /**
     * Obtiene el elemento root del XML.
     *
     * @param xml XML a leer con XPath.
     * @return El elemento root del XML o null en caso de error.
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 23/09/2014
     */
    public static Element obtenerRoot(InputStream xml) {
        try {
            Document xmlDocument = obtenerDocumento(xml);
            return xmlDocument.getDocumentElement();
        } catch (Exception ex) {
            log.error("Error en XPathUtil.", ex);
            return null;
        }
    }

    /**
     * Obtiene el elemento root del XML.
     *
     * @param xml XML a leer con XPath.
     * @return El elemento root del XML o null en caso de error.
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 23/09/2014
     */
    public static Element obtenerRoot(String xml) {
        try {
            Document xmlDocument = obtenerDocumento(xml);
            return xmlDocument.getDocumentElement();
        } catch (Exception ex) {
            log.error("Error en XPathUtil.", ex);
            return null;
        }
    }

    /**
     * Obtiene el valor de un elemento XML como String.
     *
     * @param xml XML a leer con XPath.
     * @param expression Expresion de XPath a utilizar.
     * @return El valor en String.
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 23/09/2014
     */
    public static String obtenerString(String xml, String expression) {
        try {
            Document xmlDocument = obtenerDocumento(xml);
            XPath xPath = XPathFactory.newInstance().newXPath();
            return (String) xPath
                    .compile(expression)
                    .evaluate(xmlDocument, XPathConstants.STRING);
        } catch (Exception ex) {
            log.error("Error en XPathUtil.", ex);
            return null;
        }
    }

    /**
     * Obtiene el valor de un elemento XML como String.
     *
     * @param elemento Elemento a leer con XPath.
     * @param expression Expresion de XPath a utilizar.
     * @return El valor en String.
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 23/09/2014
     */
    public static String obtenerString(Element elemento, String expression) {
        try {
            XPath xPath = XPathFactory.newInstance().newXPath();
            return (String) xPath
                    .compile(expression)
                    .evaluate(elemento, XPathConstants.STRING);
        } catch (Exception ex) {
            log.error("Error en XPathUtil.", ex);
            return null;
        }
    }

    /**
     * Obtiene el valor de un elemento XML como Boolean.
     *
     * @param xml XML a leer con XPath.
     * @param expression Expresion de XPath a utilizar.
     * @return El valor en Boolean.
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 23/09/2014
     */
    public static Boolean obtenerBoolean(String xml, String expression) {
        try {
            Document xmlDocument = obtenerDocumento(xml);
            XPath xPath = XPathFactory.newInstance().newXPath();
            return (Boolean) xPath
                    .compile(expression)
                    .evaluate(xmlDocument, XPathConstants.BOOLEAN);
        } catch (Exception ex) {
            log.error("Error en XPathUtil.", ex);
            return null;
        }
    }

    /**
     * Obtiene el valor de un elemento XML como Boolean.
     *
     * @param elemento Elemento a leer con XPath.
     * @param expression Expresion de XPath a utilizar.
     * @return El valor en Boolean.
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 23/09/2014
     */
    public static Boolean obtenerBoolean(Element elemento, String expression) {
        try {
            XPath xPath = XPathFactory.newInstance().newXPath();
            return (Boolean) xPath
                    .compile(expression)
                    .evaluate(elemento, XPathConstants.BOOLEAN);
        } catch (Exception ex) {
            log.error("Error en XPathUtil.", ex);
            return null;
        }
    }

    /**
     * Obtiene el valor de un elemento XML como Number.
     *
     * @param xml XML a leer con XPath.
     * @param expression Expresion de XPath a utilizar.
     * @return El valor en Number.
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 23/09/2014
     */
    public static Number obtenerNumber(String xml, String expression) {
        try {
            Document xmlDocument = obtenerDocumento(xml);
            XPath xPath = XPathFactory.newInstance().newXPath();
            return (Number) xPath
                    .compile(expression)
                    .evaluate(xmlDocument, XPathConstants.NUMBER);
        } catch (Exception ex) {
            log.error("Error en XPathUtil.", ex);
            return null;
        }
    }

    /**
     * Obtiene el valor de un elemento XML como Number.
     *
     * @param elemento Elemento a leer con XPath.
     * @param expression Expresion de XPath a utilizar.
     * @return El valor en Number.
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 23/09/2014
     */
    public static Number obtenerNumber(Element elemento, String expression) {
        try {
            XPath xPath = XPathFactory.newInstance().newXPath();
            return (Number) xPath
                    .compile(expression)
                    .evaluate(elemento, XPathConstants.NUMBER);
        } catch (Exception ex) {
            log.error("Error en XPathUtil.", ex);
            return null;
        }
    }

    /**
     * Obtiene el valor de un elemento XML como Element.
     *
     * @param xml XML a leer con XPath.
     * @param expression Expresion de XPath a utilizar.
     * @return El valor en Element.
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 23/09/2014
     */
    public static Element obtenerElemento(String xml, String expression) {
        try {
            Document xmlDocument = obtenerDocumento(xml);
            XPath xPath = XPathFactory.newInstance().newXPath();
            Node nodo = (Node) xPath
                    .compile(expression)
                    .evaluate(xmlDocument, XPathConstants.NODE);
            if (nodo == null) {
                return null;
            } else if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                return (Element) nodo;
            } else {
                log.error("Error en XPathUtil. Tipo de nodo {}", nodo.getNodeType());
                return null;
            }
        } catch (Exception ex) {
            log.error("Error en XPathUtil.", ex);
            return null;
        }
    }

    /**
     * Obtiene el valor de un elemento XML como Element.
     *
     * @param elemento Elemento a leer con XPath.
     * @param expression Expresion de XPath a utilizar.
     * @return El valor en Element.
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 23/09/2014
     */
    public static Element obtenerElemento(Element elemento, String expression) {
        try {
            XPath xPath = XPathFactory.newInstance().newXPath();
            Node nodo = (Node) xPath
                    .compile(expression)
                    .evaluate(elemento, XPathConstants.NODE);
            if (nodo == null) {
                return null;
            } else if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                return (Element) nodo;
            } else {
                log.error("Error en XPathUtil. Tipo de nodo {}", nodo.getNodeType());
                return null;
            }
        } catch (Exception ex) {
            log.error("Error en XPathUtil.", ex);
            return null;
        }
    }

    /**
     * Obtiene los valores de un grupo de elemento XML como List<Element>.
     *
     * @param xml XML a leer con XPath.
     * @param expression Expresion de XPath a utilizar.
     * @return Lista de Element.
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 23/09/2014
     */
    public static List<Element> obtenerElementos(String xml, String expression) {
        try {
            Document xmlDocument = obtenerDocumento(xml);
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodos = (NodeList) xPath
                    .compile(expression)
                    .evaluate(xmlDocument, XPathConstants.NODESET);

            ArrayList<Element> elementos = new ArrayList<>(nodos.getLength());

            for (int i = 0; i < nodos.getLength(); i++) {
                Node nodo = nodos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    elementos.add((Element) nodo);
                } else {
                    log.error("Error en XPathUtil. Tipo de nodo {}", nodo.getNodeType());
                }
            }
            return elementos;
        } catch (Exception ex) {
            log.error("Error en XPathUtil.", ex);
            return null;
        }
    }

    /**
     * Obtiene los valores de un grupo de elemento XML como List<Element>.
     *
     * @param elemento Elemento a leer con XPath.
     * @param expression Expresion de XPath a utilizar.
     * @return Lista de Element.
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 23/09/2014
     */
    public static List<Element> obtenerElementos(Element elemento, String expression) {
        try {
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodos = (NodeList) xPath
                    .compile(expression)
                    .evaluate(elemento, XPathConstants.NODESET);

            ArrayList<Element> elementos = new ArrayList<>(nodos.getLength());

            for (int i = 0; i < nodos.getLength(); i++) {
                Node nodo = nodos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    elementos.add((Element) nodo);
                } else {
                    log.error("Error en XPathUtil. Tipo de nodo {}", nodo.getNodeType());
                }
            }
            return elementos;
        } catch (Exception ex) {
            log.error("Error en XPathUtil.", ex);
            return null;
        }
    }

    /**
     * Obtiene los valores String de un grupo de elemento XML como List<String>.
     *
     * @param xml XML a leer con XPath.
     * @param expression Expresion de XPath a utilizar.
     * @return Lista de String.
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 23/09/2014
     */
    public static List<String> obtenerStrings(String xml, String expression) {
        try {
            Document xmlDocument = obtenerDocumento(xml);
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodos = (NodeList) xPath
                    .compile(expression)
                    .evaluate(xmlDocument, XPathConstants.NODESET);

            ArrayList<String> elementos = new ArrayList<>(nodos.getLength());

            for (int i = 0; i < nodos.getLength(); i++) {
                Node nodo = nodos.item(i);
                switch (nodo.getNodeType()) {
                    case Node.TEXT_NODE:
                        elementos.add(nodo.getNodeValue());
                        break;
                    case Node.ELEMENT_NODE:
                        elementos.add(obtenerValorElemento((Element) nodo));
                        break;
                    case Node.ATTRIBUTE_NODE:
                        Attr attribute = (Attr) nodo;
                        elementos.add(attribute.getValue());
                        break;
                    default:
                        log.error("Error en XPathUtil. Tipo de nodo {}", nodo.getNodeType());
                        break;
                }
            }
            return elementos;
        } catch (Exception ex) {
            log.error("Error en XPathUtil.", ex);
            return null;
        }
    }

    /**
     * Obtiene los valores String de un grupo de elemento XML como List<String>.
     *
     * @param elemento Elemento a leer con XPath.
     * @param expression Expresion de XPath a utilizar.
     * @return Lista de String.
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 23/09/2014
     */
    public static List<String> obtenerStrings(Element elemento, String expression) {
        try {
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodos = (NodeList) xPath
                    .compile(expression)
                    .evaluate(elemento, XPathConstants.NODESET);

            ArrayList<String> elementos = new ArrayList<>(nodos.getLength());

            for (int i = 0; i < nodos.getLength(); i++) {
                Node nodo = nodos.item(i);
                switch (nodo.getNodeType()) {
                    case Node.TEXT_NODE:
                        elementos.add(nodo.getNodeValue());
                        break;
                    case Node.ELEMENT_NODE:
                        elementos.add(obtenerValorElemento((Element) nodo));
                        break;
                    case Node.ATTRIBUTE_NODE:
                        Attr attribute = (Attr) nodo;
                        elementos.add(attribute.getValue());
                        break;
                    default:
                        log.error("Error en XPathUtil. Tipo de nodo {}", nodo.getNodeType());
                        break;
                }
            }
            return elementos;
        } catch (Exception ex) {
            log.error("Error en XPathUtil.", ex);
            return null;
        }
    }
}
