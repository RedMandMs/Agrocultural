package ru.lenoblgis.introduse.sergey.validation.annotation.organization;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ru.lenoblgis.introduse.sergey.validation.implementation.organization.NewINNOrganizationValidator;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=NewINNOrganizationValidator.class)
public @interface NonCopyINNOrganization {
	String message() default "CopyINN";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
}
