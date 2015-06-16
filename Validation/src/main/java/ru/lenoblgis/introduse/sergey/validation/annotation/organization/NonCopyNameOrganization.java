package ru.lenoblgis.introduse.sergey.validation.annotation.organization;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ru.lenoblgis.introduse.sergey.validation.implementation.organization.NewNameOrganizationValidator;
import static java.lang.annotation.ElementType.*;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=NewNameOrganizationValidator.class)
public @interface NonCopyNameOrganization {
	String message() default "{ru.lenoblgis.introduse.sergey.verefication.annotation.organization.NewNameOrganization.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
}
