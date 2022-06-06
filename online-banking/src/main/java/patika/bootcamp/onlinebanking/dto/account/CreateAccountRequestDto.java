package patika.bootcamp.onlinebanking.dto.account;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.enums.AccountType;

@Getter
@Setter
public class CreateAccountRequestDto {
	
	@NotNull
	private AccountType accountType;
	
	@NotNull
	private Long customerId;
	
	@NotNull
	private Long currencyId;
	
	@NotNull
	private Long branchId;
}
