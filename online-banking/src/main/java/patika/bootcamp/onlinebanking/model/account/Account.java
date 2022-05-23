package patika.bootcamp.onlinebanking.model.account;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.base.BaseExtendedModel;
import patika.bootcamp.onlinebanking.model.enums.AccountStatus;

@Getter
@Setter
@MappedSuperclass
public abstract class Account extends BaseExtendedModel{
	
	private Long accountNumber;
	private BigDecimal accountBalance = BigDecimal.ZERO;
	
	@PositiveOrZero
	private BigDecimal lockedBalance = BigDecimal.ZERO;
	private String bankCode;
	private String branchCode;
	
	//@Pattern(regexp = "")
	private String iban;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date blockedAt;
	
	@FutureOrPresent
	@Temporal(TemporalType.TIMESTAMP)
	private Date canBeActiveAt;
	
	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus = AccountStatus.PASSIVE;
	
	//@Enumerated(EnumType.STRING)
	//private CurrencyUnit currencyUnit;
	//private boolean isActive = true; bunu AccountStatus ile yaptık yuk
}
