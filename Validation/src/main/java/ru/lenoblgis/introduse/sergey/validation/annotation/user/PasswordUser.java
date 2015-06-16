package ru.lenoblgis.introduse.sergey.validation.annotation.user;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ru.lenoblgis.introduse.sergey.validation.implementation.user.PasswordUserValidator;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=PasswordUserValidator.class)
public @interface PasswordUser {
	String message() default "WrongFormatPassword";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
}
