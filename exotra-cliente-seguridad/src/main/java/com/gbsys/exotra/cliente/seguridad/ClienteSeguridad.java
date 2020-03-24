package com.gbsys.exotra.cliente.seguridad;

import java.io.Serializable;
import java.io.StringReader;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Cliente para realizar la conexión al servicio web de seguridad.
 *
 * @author Ing. Melanie Morales Arroyo.
 * @version 1.0.0
 * @date 10/07/2018
 */
@Slf4j
public class ClienteSeguridad implements Serializable {

    private static final String ERROR_GENERICO = "Error al obtener respuesta.";

    private final Seguridad seguridad;

    /**
     * Constructor del cliente para realizar la conexión al servicio web de
     * seguridad.
     *
     * @param api URL del servicio web.
     * @author Ing. Melanie Morales Arroyo.
     * @since 10/07/2018
     * @version 1.0.0
     */
    public ClienteSeguridad(String api) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        seguridad = retrofit.create(Seguridad.class);
    }

    /**
     * Método encargado de realizar el login de un determinado usuario.
     *
     * @param usuario Código del usuario a realizar login.
     * @param clave Clave del usuario a realizar login.
     * @return Listado de recursos asociados al usuario.
     * @throws Exception
     * @author Ing. Melanie Morales Arroyo
     * @since 10/07/2018
     * @version 1.0.0
     */
    public List<String> recursos(String usuario, String clave) throws Exception {
        ModeloAutenticacion modeloAutenticacion = new ModeloAutenticacion();
        modeloAutenticacion.setUsuario(usuario);
        modeloAutenticacion.setClave(clave);
        List<String> respuesta = respuesta(seguridad.recursos(modeloAutenticacion));
        return respuesta;
    }

    public ModeloUsuarioExterno credenciales(String usuario, String clave) throws Exception {
        ModeloAutenticacion modeloAutenticacion = new ModeloAutenticacion();
        modeloAutenticacion.setUsuario(usuario);
        modeloAutenticacion.setClave(clave);
        ModeloUsuarioExterno respuesta = respuesta(seguridad.credenciales(modeloAutenticacion));
        return respuesta;
    }

    public List<ModeloUsuarioExterno> obtenerUsuarios(long idSistema) throws Exception {
        List<ModeloUsuarioExterno> respuesta = respuesta(seguridad.obtenerUsuario(idSistema));
        return respuesta;
    }

    /**
     * Método encargado de realizar el llamado al servicio web de Seguridad,
     * utilizando para ello Retrofit. Obtiene la respuesta en el tipo de datos
     * indicado, en caso de error retorna la excepción con el error y el
     * mensaje.
     *
     * @param <T> tipo de dato genérico.
     * @param llamado llamado al API de Easy ATV por medio de Retrofit.
     * @return respuesta en el tipo de dato indicado.
     * @throws EasyAtvException en caso de algún error con el API de Easy ATV.
     */
    private <T> T respuesta(Call<T> llamado) throws Exception {
        try {
            Response<T> respuesta = llamado.execute();
            if (respuesta.isSuccessful()) {
                return respuesta.body();
            } else {
                try {
                    if (respuesta.errorBody() != null) {
                        String json = respuesta.errorBody().string();
                        JsonReader jsonReader = Json.createReader(new StringReader(json));
                        JsonObject jsonObject = jsonReader.readObject();
                        jsonReader.close();

                        MensajeError mensajeError = new MensajeError();
                        mensajeError.setCodigo(jsonObject.getInt("codigo"));
                        mensajeError.setDescripcion(jsonObject.getString("descripcion"));

                        throw new Exception(mensajeError.getDescripcion());
                    }
                    throw new Exception(ERROR_GENERICO);
                } catch (Exception ex) {
                    log.error(ex.getMessage(), ex);
                    throw new SeguridadClienteException(ex.getMessage());

                }
            }
        } catch (Exception ex) {
            log.error(ERROR_GENERICO, ex);
            throw ex;
        }
    }
}
