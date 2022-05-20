package patika.bootcamp.onlinebanking.dto.customer;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.customvalidation.PhoneNumber;
import patika.bootcamp.onlinebanking.model.customer.Gender;

@Getter
@Setter
public class CreateCustomerRequestDto {
	private String email;
	private String identityNumber;
	
	@PhoneNumber(message = "Enter the complete phone number with +90 at the beginning.")
	private String phoneNumber;
	private String password;
	
	@Min(value = 18, message = "Persons under the age of 18 cannot be bank customers.")
	private Integer age;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
}
