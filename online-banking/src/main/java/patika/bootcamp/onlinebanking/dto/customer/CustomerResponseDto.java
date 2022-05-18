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
import patika.bootcamp.onlinebanking.model.customer.Gender;

@Getter
@Setter
public class CustomerResponseDto {
	private String email;
	private String identityNumber;
	private String phoneNumber;
	private String password;
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
