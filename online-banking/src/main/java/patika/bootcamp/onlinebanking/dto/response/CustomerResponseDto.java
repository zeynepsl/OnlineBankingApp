package patika.bootcamp.onlinebanking.dto.response;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.enums.Gender;

@Getter
@Setter
public class CustomerResponseDto {
	private Long id;
	private String email;
	private String phoneNumber;
	private String secondaryEmail;
	private String secondaryPhoneNumber;
	private Integer age;
	private Gender gender;
	private boolean isActive;
	private boolean isConfirmedByAdmin;
	
	private Set<AccountResponseDto> accountResponseDtos = new HashSet<>();
	
	private CreditCardResponseDto creditCardResponseDto;
	
	private PrepaidCardResponseDto prepaidCardResponseDto;
}
