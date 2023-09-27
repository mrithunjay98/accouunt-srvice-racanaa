package com.racanaa.services.account.dto.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static com.racanaa.services.account.constant.ApiConstant.VALIDATION_MSG_INVALID_CONTACT;

/**
 * Defining new validation constraint for contact number
 *
 * @author Manoahr
 * @since 1.0
 */
@Documented
@Constraint(validatedBy = ContactNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ContactNumberConstraint {
    String message() default VALIDATION_MSG_INVALID_CONTACT;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}