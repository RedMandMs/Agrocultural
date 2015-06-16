package ru.lenoblgis.introduse.sergey.validation.annotation.user;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ru.lenoblgis.introduse.sergey.validation.implementation.user.NewLoginUserValidator;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=NewLoginUserValidator.class)
public @interface NewLoginUser {
	String message() default "{ru.lenoblgis.introduse.sergey.verefication.annotation.user.NewLoginUser.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
}
