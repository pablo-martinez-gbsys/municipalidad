package com.gbsys.exotra.util;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Utilitario para realizar validaciones en código con BeanValidation.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 25/05/2018 12:03
 */
@Slf4j
@UtilityClass
public class BeanValidationUtil {

    /**
     * Valida un POJO anotado con BeanValidation.
     * <pre><code>
     * Persona persona = new Persona(null);
     * BeanValidationUtil.validar(persona);
     * </code></pre>
     *
     * @param <T> tipo genérico
     * @param valor objeto a validar
     * @param grupos grupo de validación (opcional), por defecto Default
     * @return Lista de errores de validación
     */
    public static <T> Set<ConstraintViolation<T>> validar(T valor, Class<?>... grupos) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(valor, grupos);
    }

    /**
     * Valida una propiedad de un POJO anotado con BeanValidation.
     * <pre><code>
     * Persona persona = new Persona(null);
     * BeanValidationUtil.validarPropiedad(persona, "nombre");
     * </code></pre>
     *
     * @param <T> tipo genérico
     * @param valor objeto a validar
     * @param propiedad propiedad a validar
     * @param grupos grupo de validación (opcional), por defecto Default
     * @return Lista de errores de validación
     */
    public static <T> Set<ConstraintViolation<T>> validarPropiedad(T valor, String propiedad, Class<?>... grupos) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validateProperty(valor, propiedad, grupos);
    }

    /**
     * Valida un valor para una propiedad de un POJO anotado con BeanValidation.
     * <pre><code>
     * BeanValidationUtil.validarValor(Persona.class, "nombre", null);
     * </code></pre>
     *
     * @param <T> tipo genérico
     * @param clase Clase del POJO a validar
     * @param propiedad propiedad a validar
     * @param valor valor a validar
     * @param grupos grupo de validación (opcional), por defecto Default
     * @return Lista de errores de validación
     */
    public static <T> Set<ConstraintViolation<T>> validarValor(Class<T> clase, String propiedad, T valor, Class<?>... grupos) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validateValue(clase, propiedad, valor, grupos);
    }
}
