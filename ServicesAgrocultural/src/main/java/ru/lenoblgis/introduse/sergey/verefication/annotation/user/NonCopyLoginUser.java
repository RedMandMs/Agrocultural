package ru.lenoblgis.introduse.sergey.verefication.annotation.user;

import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ru.lenoblgis.introduse.sergey.verefication.implimentation.user.NonCopyLoginUserValidator;

@Target({LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=NonCopyLoginUserValidator.class)
public @interface NonCopyLoginUser {
	String message() default "{ru.lenoblgis.introduse.sergey.verefication.annotation.user.NonCopyLoginUser.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
}
