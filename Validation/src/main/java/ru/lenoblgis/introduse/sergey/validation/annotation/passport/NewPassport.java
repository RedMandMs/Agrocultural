package ru.lenoblgis.introduse.sergey.validation.annotation.passport;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ru.lenoblgis.introduse.sergey.validation.implementation.user.NewPasswordUserValidator;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=NewPasswordUserValidator.class)
public @interface NewPassport {
	String message() default "{ru.lenoblgis.introduse.sergey.verefication.annotation.passport.NewPassport.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
}
