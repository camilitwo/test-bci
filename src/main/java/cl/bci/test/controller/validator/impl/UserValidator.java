package cl.bci.test.controller.validator.impl;

import cl.bci.test.controller.validator.UserValidation;
import cl.bci.test.dto.UserRequestDTO;
import org.apache.commons.lang3.StringUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserValidator implements ConstraintValidator<UserValidation, UserRequestDTO> {

    @Override
    public void initialize(UserValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRequestDTO userRequestDTO, ConstraintValidatorContext context) {
        boolean isValid = true;

        if (userRequestDTO == null) {
            return isValid; // or handle null case as needed
        }

        // Validar el campo "name"
        if (StringUtils.isBlank(userRequestDTO.getName())) {
            isValid = false;
            addConstraintViolation(context, "name", "name es requerido");
        }

        // Validar el campo "email"
        if (StringUtils.isBlank(userRequestDTO.getEmail())) {
            isValid = false;
            addConstraintViolation(context, "email", "email es requerido");
        }else{
            if(!userRequestDTO.getEmail().matches("^(.+)@(.+)$")){
                isValid = false;
                addConstraintViolation(context, "email", "email no tiene un formato correcto");
            }
        }

        // Validar el campo "password"
        if (StringUtils.isBlank(userRequestDTO.getPassword())) {
            isValid = false;
            addConstraintViolation(context, "password", "password es requerido");
        }

        return isValid;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String field, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(field)
                .addConstraintViolation();
    }
}
