package com.gbsys.exotra.util.correo;

import com.gbsys.exotra.exception.UtilException;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import javax.mail.Session;
import javax.mail.util.ByteArrayDataSource;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.HtmlEmail;

/**
 * Servicio encargado de enviar correos.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 02/05/2017 11:00
 */
@Slf4j
@Stateless
public class ServicioCorreo {

    @Resource(name = "mail/exotra")
    private Session session;

    /**
     * Método encargado de enviar el correo
     *
     * @param correo Correo donde se va enviar. (Destino)
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 02/05/2017 11:00
     */
    @TransactionAttribute(value = NOT_SUPPORTED)
    public void enviar(@NonNull Correo correo) {
        try {
            HtmlEmail htmlEmail = new HtmlEmail();
            htmlEmail.setMailSession(session);
            //Agregar los contactos
            for (String contacto : correo.getContactos()) {
                htmlEmail.addTo(contacto);
            }
            //Agregar los con copia
            for (String copia : correo.getCopias()) {
                htmlEmail.addCc(copia);
            }
            //Agregar los con copia oculta
            for (String copiaOculta : correo.getCopiasOcultas()) {
                htmlEmail.addBcc(copiaOculta);
            }
            //Agrega los adjuntos
            for (Adjunto adjunto : correo.getAdjuntos()) {
                htmlEmail.attach(
                        new ByteArrayDataSource(
                                adjunto.getArchivo(),
                                adjunto.getFormato().getMimeType()),
                        adjunto.getNombre(),
                        adjunto.getDescripcion());
            }
            //Agregar el asunto y cuerpo del mensaje
            htmlEmail.setSubject(correo.getAsunto());
            htmlEmail.setHtmlMsg(correo.getMensaje());
            htmlEmail.setTextMsg(correo.getMensaje());
            //Envia el correo
            htmlEmail.send();
        } catch (Exception ex) {
            log.error("Error al enviar el correo.", ex);
            throw new UtilException("Error al enviar el correo.", ex);
        }
    }
}
