package com.gbsys.exotra.cliente.seguridad;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Interfaz con los métodos públicos del servicio web de seguridad.
 *
 * @author Ing. Melanie Morales Arroyo
 * @since 10/07/2018
 * @version 1.0.0
 */
public interface Seguridad {

    /**
     * Método encargado de realizar el login de un determinado usuario.
     *
     * @param modelo Datos del usuario a realizar login.
     * @return Listado de recursos asociados al usuario.
     * @author Ing. Melanie Morales Arroyo
     * @since 10/07/2018
     * @version 1.0.0
     */
    @POST("recursos")
    Call<List<String>> recursos(@Body ModeloAutenticacion modelo);

    /**
     * Método encargado de realizar el login de un determinado usuario.
     *
     * @param modelo Datos del usuario a realizar login.
     * @return Listado de recursos asociados al usuario.
     * @author Ing. Melanie Morales Arroyo
     * @since 10/07/2018
     * @version 1.0.0
     */
    @POST("datosUsuario")
    Call<ModeloUsuarioExterno> credenciales(@Body ModeloAutenticacion modelo);

    /**
     * Método encargado de realizar el login de un determinado usuario.
     *
     * @param idSistema Dato del sistema a consultar.
     * @return Listado de recursos asociados al usuario.
     * @author Gustavo Martinez Torres
     * @since 10/07/2018
     * @version 1.0.0
     */
    @GET("consulta/{idSistema}/usuario")
    Call<List<ModeloUsuarioExterno>> obtenerUsuario(@Path("idSistema") long idSistema);
}
