package patika.bootcamp.onlinebanking.validator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.exception.CreditCardServiceOperationException;

@Component
@RequiredArgsConstructor
public class PasswordValidator {
	private final PasswordEncoder passwordEncoder;
	
	public void validate(String inputPassword, String encodedPassword) throws BaseException {
		boolean isMatched = passwordEncoder.matches(inputPassword, encodedPassword);
		if (!isMatched) {
			throw new CreditCardServiceOperationException.PasswordWrong("The password you entered is incorrect");
		}
	}
}
