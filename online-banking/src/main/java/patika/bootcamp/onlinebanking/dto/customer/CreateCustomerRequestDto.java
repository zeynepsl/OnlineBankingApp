package patika.bootcamp.onlinebanking.dto.customer;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.customvalidation.PhoneNumber;
import patika.bootcamp.onlinebanking.model.customer.Gender;

@Getter
@Setter
public class CreateCustomerRequestDto {
	@NotBlank(message = "email cannot be null and empty")
	private String email;
	
	@NotBlank(message = "identityNumber cannot be null and empty")
	private String identityNumber;
	
	@PhoneNumber(message = "Enter the complete phone number with +90 at the beginning.")
	private String phoneNumber;
	
	@NotBlank(message = "password cannot be null and empty")
	private String password;
	
	@Min(value = 18, message = "Persons under the age of 18 cannot be bank customers.")
	private Integer age;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Gender gender;
}
