
package com.gbsys.exotra.seguridad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.gbsys.exotra.seguridad package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _String_QNAME = new QName("http://tempuri.org/", "string");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gbsys.exotra.seguridad
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IngresoUsuario }
     * 
     */
    public IngresoUsuario createIngresoUsuario() {
        return new IngresoUsuario();
    }

    /**
     * Create an instance of {@link IngresoUsuarioResponse }
     * 
     */
    public IngresoUsuarioResponse createIngresoUsuarioResponse() {
        return new IngresoUsuarioResponse();
    }

    /**
     * Create an instance of {@link CrearTramite }
     * 
     */
    public CrearTramite createCrearTramite() {
        return new CrearTramite();
    }

    /**
     * Create an instance of {@link CrearTramiteResponse }
     * 
     */
    public CrearTramiteResponse createCrearTramiteResponse() {
        return new CrearTramiteResponse();
    }

    /**
     * Create an instance of {@link LoginTramitesWeb }
     * 
     */
    public LoginTramitesWeb createLoginTramitesWeb() {
        return new LoginTramitesWeb();
    }

    /**
     * Create an instance of {@link LoginTramitesWebResponse }
     * 
     */
    public LoginTramitesWebResponse createLoginTramitesWebResponse() {
        return new LoginTramitesWebResponse();
    }

    /**
     * Create an instance of {@link ValidacionUsuarioLogueado }
     * 
     */
    public ValidacionUsuarioLogueado createValidacionUsuarioLogueado() {
        return new ValidacionUsuarioLogueado();
    }

    /**
     * Create an instance of {@link ValidacionUsuarioLogueadoResponse }
     * 
     */
    public ValidacionUsuarioLogueadoResponse createValidacionUsuarioLogueadoResponse() {
        return new ValidacionUsuarioLogueadoResponse();
    }

    /**
     * Create an instance of {@link CatalogoTiposTramites }
     * 
     */
    public CatalogoTiposTramites createCatalogoTiposTramites() {
        return new CatalogoTiposTramites();
    }

    /**
     * Create an instance of {@link CatalogoTiposTramitesResponse }
     * 
     */
    public CatalogoTiposTramitesResponse createCatalogoTiposTramitesResponse() {
        return new CatalogoTiposTramitesResponse();
    }

    /**
     * Create an instance of {@link CatalogoRequisitosTipoTramite }
     * 
     */
    public CatalogoRequisitosTipoTramite createCatalogoRequisitosTipoTramite() {
        return new CatalogoRequisitosTipoTramite();
    }

    /**
     * Create an instance of {@link CatalogoRequisitosTipoTramiteResponse }
     * 
     */
    public CatalogoRequisitosTipoTramiteResponse createCatalogoRequisitosTipoTramiteResponse() {
        return new CatalogoRequisitosTipoTramiteResponse();
    }

    /**
     * Create an instance of {@link PropiedadesIdentificacion }
     * 
     */
    public PropiedadesIdentificacion createPropiedadesIdentificacion() {
        return new PropiedadesIdentificacion();
    }

    /**
     * Create an instance of {@link PropiedadesIdentificacionResponse }
     * 
     */
    public PropiedadesIdentificacionResponse createPropiedadesIdentificacionResponse() {
        return new PropiedadesIdentificacionResponse();
    }

    /**
     * Create an instance of {@link InsercionDocumentos }
     * 
     */
    public InsercionDocumentos createInsercionDocumentos() {
        return new InsercionDocumentos();
    }

    /**
     * Create an instance of {@link InsercionDocumentosResponse }
     * 
     */
    public InsercionDocumentosResponse createInsercionDocumentosResponse() {
        return new InsercionDocumentosResponse();
    }

    /**
     * Create an instance of {@link ConsultaTramites }
     * 
     */
    public ConsultaTramites createConsultaTramites() {
        return new ConsultaTramites();
    }

    /**
     * Create an instance of {@link ConsultaTramitesResponse }
     * 
     */
    public ConsultaTramitesResponse createConsultaTramitesResponse() {
        return new ConsultaTramitesResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

}
