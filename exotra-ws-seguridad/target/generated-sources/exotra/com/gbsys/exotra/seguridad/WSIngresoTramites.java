
package com.gbsys.exotra.seguridad;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WS_IngresoTramites", targetNamespace = "http://tempuri.org/", wsdlLocation = "http://ws-declaraciones.curridabat.go.cr/WS_IngresoTramites.asmx?WSDL")
public class WSIngresoTramites
    extends Service
{

    private final static URL WSINGRESOTRAMITES_WSDL_LOCATION;
    private final static WebServiceException WSINGRESOTRAMITES_EXCEPTION;
    private final static QName WSINGRESOTRAMITES_QNAME = new QName("http://tempuri.org/", "WS_IngresoTramites");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://ws-declaraciones.curridabat.go.cr/WS_IngresoTramites.asmx?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WSINGRESOTRAMITES_WSDL_LOCATION = url;
        WSINGRESOTRAMITES_EXCEPTION = e;
    }

    public WSIngresoTramites() {
        super(__getWsdlLocation(), WSINGRESOTRAMITES_QNAME);
    }

    public WSIngresoTramites(WebServiceFeature... features) {
        super(__getWsdlLocation(), WSINGRESOTRAMITES_QNAME, features);
    }

    public WSIngresoTramites(URL wsdlLocation) {
        super(wsdlLocation, WSINGRESOTRAMITES_QNAME);
    }

    public WSIngresoTramites(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WSINGRESOTRAMITES_QNAME, features);
    }

    public WSIngresoTramites(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSIngresoTramites(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WSIngresoTramitesSoap
     */
    @WebEndpoint(name = "WS_IngresoTramitesSoap")
    public WSIngresoTramitesSoap getWSIngresoTramitesSoap() {
        return super.getPort(new QName("http://tempuri.org/", "WS_IngresoTramitesSoap"), WSIngresoTramitesSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WSIngresoTramitesSoap
     */
    @WebEndpoint(name = "WS_IngresoTramitesSoap")
    public WSIngresoTramitesSoap getWSIngresoTramitesSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "WS_IngresoTramitesSoap"), WSIngresoTramitesSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WSINGRESOTRAMITES_EXCEPTION!= null) {
            throw WSINGRESOTRAMITES_EXCEPTION;
        }
        return WSINGRESOTRAMITES_WSDL_LOCATION;
    }

}