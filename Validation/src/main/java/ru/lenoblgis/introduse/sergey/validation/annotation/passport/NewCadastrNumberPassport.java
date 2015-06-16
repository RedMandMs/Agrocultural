package ru.lenoblgis.introduse.sergey.validation.annotation.passport;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ru.lenoblgis.introduse.sergey.validation.implementation.passport.NewCadastrNumberPassportValidator;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=NewCadastrNumberPassportValidator.class)
public @interface NewCadastrNumberPassport {
	String message() default "{ru.lenoblgis.introduse.sergey.verefication.annotation.passport.NewCadastrNumberPassport.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
}
