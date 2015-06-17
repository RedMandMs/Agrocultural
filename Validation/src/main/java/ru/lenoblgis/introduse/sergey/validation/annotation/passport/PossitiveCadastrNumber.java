package ru.lenoblgis.introduse.sergey.validation.annotation.passport;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ru.lenoblgis.introduse.sergey.validation.implementation.passport.PossitiveCadastrNumberValidator;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=PossitiveCadastrNumberValidator.class)
public @interface PossitiveCadastrNumber {
	String message() default "NegativCadastrNumber";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};	
}
