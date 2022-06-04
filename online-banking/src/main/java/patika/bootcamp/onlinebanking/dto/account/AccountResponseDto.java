package patika.bootcamp.onlinebanking.dto.account;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.enums.AccountStatus;
import patika.bootcamp.onlinebanking.model.enums.AccountType;

@Getter
@Setter
public class AccountResponseDto {
	private Long id;
	private String accountNumber;
	private BigDecimal accountBalance;
	private String bankCode;

	private String iban;
	private Date blockedAt;
	private Date canBeActiveAt;
	private AccountStatus accountStatus;
	private AccountType accountType;
	private Long currencyId;
	private Long customerId;
	private Long branchId;
}
