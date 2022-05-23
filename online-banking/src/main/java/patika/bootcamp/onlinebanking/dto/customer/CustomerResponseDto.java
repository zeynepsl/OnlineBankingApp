package patika.bootcamp.onlinebanking.dto.customer;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.dto.account.GoldPointAccountResponseDto;
import patika.bootcamp.onlinebanking.dto.account.PrimaryAccountResponseDto;
import patika.bootcamp.onlinebanking.dto.account.SavingsAccountResponseDto;
import patika.bootcamp.onlinebanking.dto.card.CreditCardResponseDto;
import patika.bootcamp.onlinebanking.dto.card.PrepaidCardResponseDto;
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
	
	private Set<PrimaryAccountResponseDto> primaryAccountResponseDtos = new HashSet<PrimaryAccountResponseDto>();
	
	private Set<SavingsAccountResponseDto> savingsAccountResponseDtos = new HashSet<SavingsAccountResponseDto>();
	
	private CreditCardResponseDto creditCardResponseDto;
	
	private PrepaidCardResponseDto prepaidCardResponseDto;
	
	private GoldPointAccountResponseDto goldPointAccountResponseDto;
}
