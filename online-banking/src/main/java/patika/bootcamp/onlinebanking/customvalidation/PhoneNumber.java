package patika.bootcamp.onlinebanking.customvalidation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
	String message() default "Invalid phone number";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
