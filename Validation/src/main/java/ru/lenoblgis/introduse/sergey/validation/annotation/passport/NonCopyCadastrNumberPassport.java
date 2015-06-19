package ru.lenoblgis.introduse.sergey.validation.annotation.passport;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ru.lenoblgis.introduse.sergey.validation.implementation.passport.NonCopyCadastrNumberPassportValidator;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=NonCopyCadastrNumberPassportValidator.class)
public @interface NonCopyCadastrNumberPassport {
	String message() default "CopyCadastrNumber";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
}
