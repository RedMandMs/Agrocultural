package ru.lenoblgis.introduse.sergey.verefication.annotation.organization;

import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ru.lenoblgis.introduse.sergey.verefication.implimentation.organization.NewINNOrganizationValidator;

@Target({LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=NewINNOrganizationValidator.class)
public @interface NewINNOrganization {
	String message() default "{ru.lenoblgis.introduse.sergey.verefication.annotation.organization.NewINNOrganization.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
}
