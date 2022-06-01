package patika.bootcamp.onlinebanking.dto.customer;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.dto.account.AccountResponseDto;
import patika.bootcamp.onlinebanking.dto.card.CreditCardResponseDto;
import patika.bootcamp.onlinebanking.dto.card.PrepaidCardResponseDto;
import patika.bootcamp.onlinebanking.model.enums.Gender;

@Getter
@Setter
public class CustomerResponseDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String secondaryEmail;
	private String secondaryPhoneNumber;
	private String customerNumber;
	private Integer age;
	private Date birthDate;
	private Gender gender;
	private boolean isActive;
	private boolean isConfirmedByAdmin;
	
	//private Set<AccountResponseDto> accountResponseDtos = new HashSet<>();
	
	//private Set<CustomerAddressResponseDto> customerAddressResponseDtos = new HashSet<>();
	
	//private CreditCardResponseDto creditCardResponseDto;
	
	//private PrepaidCardResponseDto prepaidCardResponseDto;
}
