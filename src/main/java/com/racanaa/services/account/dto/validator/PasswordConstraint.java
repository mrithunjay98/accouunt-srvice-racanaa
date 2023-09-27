package com.racanaa.services.account.dto.validator;

import com.racanaa.services.account.dto.validator.todo.PassowrdValidtor2;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static com.racanaa.services.account.constant.ApiConstant.VALIDATION_MSG_INVALID_PASSWORD;

/**
 * Defining new validation constraint for password
 *
 * @author Manoahr
 * @since 1.0
 */
@Documented
@Constraint(validatedBy = PassowrdValidtor2.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default VALIDATION_MSG_INVALID_PASSWORD;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}