package ru.lenoblgis.introduse.sergey.verefication.annotation.organization;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ru.lenoblgis.introduse.sergey.verefication.implimentation.organization.NewNameOrganizationValidator;
import static java.lang.annotation.ElementType.*;

@Target({LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=NewNameOrganizationValidator.class)
public @interface NewNameOrganization {
	String message() default "{ru.lenoblgis.introduse.sergey.verefication.annotation.organization.NewNameOrganization.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
}
