package ru.lenoblgis.introduse.sergey.validation.annotation.organization;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ru.lenoblgis.introduse.sergey.validation.implementation.organization.NonCopyINNOrganizationValidator;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=NonCopyINNOrganizationValidator.class)
public @interface NonCopyINNOrganization {
	String message() default "CopyINN";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
}
