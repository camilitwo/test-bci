package cl.bci.test.controller.validator;

import cl.bci.test.controller.validator.impl.UserValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserValidator.class)
public @interface UserValidation {
    String message() default "Request mal generado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
