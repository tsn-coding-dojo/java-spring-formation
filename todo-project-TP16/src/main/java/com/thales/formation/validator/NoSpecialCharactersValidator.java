package com.thales.formation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoSpecialCharactersValidator implements ConstraintValidator<NoSpecialCharacters, String> {

    private boolean expectedTrue;
    
    private String regex = "^.*[/\\,;\\-_#].*$";

    @Override
    public void initialize(NoSpecialCharacters constraintAnnotation) {
        this.expectedTrue = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if ( object == null ) {
            return true;
        }
        boolean containsSpecialCharacters = object.matches(regex);
        return containsSpecialCharacters != this.expectedTrue;
    }
}
