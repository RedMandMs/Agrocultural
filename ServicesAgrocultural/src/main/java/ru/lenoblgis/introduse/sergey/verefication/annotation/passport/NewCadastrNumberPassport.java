package ru.lenoblgis.introduse.sergey.verefication.annotation.passport;

import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ru.lenoblgis.introduse.sergey.verefication.implimentation.passport.NewCadastrNumberPassportValidator;

@Target({LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=NewCadastrNumberPassportValidator.class)
public @interface NewCadastrNumberPassport {
	String message() default "{ru.lenoblgis.introduse.sergey.verefication.annotation.passport.NewCadastrNumberPassport.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
}
