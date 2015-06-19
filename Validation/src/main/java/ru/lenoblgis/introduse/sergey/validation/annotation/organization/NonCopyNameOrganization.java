package ru.lenoblgis.introduse.sergey.validation.annotation.organization;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ru.lenoblgis.introduse.sergey.validation.implementation.organization.NonCopyNameOrganizationValidator;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=NonCopyNameOrganizationValidator.class)
public @interface NonCopyNameOrganization {
	String message() default "CopyNameOrganization";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
}
