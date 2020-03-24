package com.gbsys.exotra.util.filtro;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 * Anotación encargada de guardar la información del campo a filtrar.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 29/11/2017 16:51
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface Filtro {

    /**
     * Nombre del campo a filtrar.
     *
     * @return nombre del campo a filtrar
     */
    String value();

    /**
     * Condición de filtrado, por defecto es IGUAL.
     *
     * @return condición de filtrado
     */
    Class<? extends Condicion> condicion() default CondicionIgual.class;

    /**
     * Indica si se debe ignorar el filtro si el valor es cero, por defecto es
     * TRUE.
     *
     * @return true si se debe ignorar el filtro, false en caso contrario.
     */
    boolean ignorarCero() default true;

    /**
     * Indica si se debe ignorar el filtro si el valor es falso, por defecto es
     * TRUE.
     *
     * @return true si se debe ignorar el filtro, false en caso contrario.
     */
    boolean ignorarFalso() default true;
}
