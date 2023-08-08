package com.thales.formation.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoSpecialCharactersValidator.class)
@Documented
public @interface NoSpecialCharacters {
	
	String message() default "Should (or should not) contain special characters";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    
    boolean value() default true;

    @Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
    	boolean[] value();
    }
}
