package patika.bootcamp.onlinebanking.dto.customer;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.customer.Gender;

@Getter
@Setter
public class CreateCustomerRequestDto {
	private String email;
	private String identityNumber;
	private String phoneNumber;
	private String password;
	
	@Min(18)
	private Integer age;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
}
