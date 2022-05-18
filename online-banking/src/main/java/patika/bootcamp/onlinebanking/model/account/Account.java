package patika.bootcamp.onlinebanking.model.account;

import java.math.BigDecimal;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.BaseExtendedModel;

@Getter
@Setter
@MappedSuperclass
public abstract class Account extends BaseExtendedModel{
	
	private String accountNumber;
	private BigDecimal accountBalance;
	
	@Enumerated(EnumType.STRING)
	private CurrencyUnit currencyUnit;
	
}
