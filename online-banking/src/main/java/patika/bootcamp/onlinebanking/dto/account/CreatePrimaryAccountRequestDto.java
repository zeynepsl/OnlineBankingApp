package patika.bootcamp.onlinebanking.dto.account;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.account.CurrencyUnit;

@Getter
@Setter
public class CreatePrimaryAccountRequestDto {
	@Enumerated(EnumType.STRING)
	private CurrencyUnit currencyUnit;
	private Long customerId;
	private Long creditCardId;
}
