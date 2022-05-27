package patika.bootcamp.onlinebanking.dto.request;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.enums.AccountType;

@Getter
@Setter
public class CreateAccountRequestDto {
	
	private String bankCode;
	private AccountType accountType;
	private Long customerId;
	private Long currencyId;
	private Long bankBranchId;
}
