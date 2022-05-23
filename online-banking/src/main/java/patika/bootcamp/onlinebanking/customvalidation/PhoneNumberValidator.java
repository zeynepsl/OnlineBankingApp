package patika.bootcamp.onlinebanking.customvalidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

	//This method is guaranteed to be called before any use of this instance for validation.
	@Override
	public void initialize(PhoneNumber constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context)  {
		if (value.startsWith("+90") && value.length() == 13 && (value.substring(1)).chars().allMatch(Character::isDigit)) {
			return true;
		}
		return false;
	}

}
