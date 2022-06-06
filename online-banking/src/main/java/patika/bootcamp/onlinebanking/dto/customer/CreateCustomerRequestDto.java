package patika.bootcamp.onlinebanking.dto.customer;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.customvalidation.PhoneNumber;
import patika.bootcamp.onlinebanking.model.enums.Gender;

@Getter
@Setter
public class CreateCustomerRequestDto {
	@NotBlank(message = "email cannot be null and empty")
	private String email;
	
	@PhoneNumber(message = "Enter the complete phone number with +90 at the beginning.")
	private String phoneNumber;
	
	@NotBlank(message = "email cannot be null and empty")
	private String secondaryEmail;
	
	@PhoneNumber(message = "Enter the complete secondary phone number with +90 at the beginning.")
	private String secondaryPhoneNumber;
	
	@NotNull(message = "identityNumber cannot be null")
	private Long identityNumber;
	
	private String firstName;
	private String lastName;
	
	@Min(value = 18, message = "Persons under the age of 18 cannot be bank customers.")
	private Integer age;
	
	private String password;
	
	@Past
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthDate;
	
	@NotNull
	private Gender gender;
	
}
