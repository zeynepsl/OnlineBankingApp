package patika.bootcamp.onlinebanking.model.card;

import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.base.BaseExtendedModel;

@Getter
@Setter
@MappedSuperclass
public abstract class Card extends BaseExtendedModel{
	private String cardNumber;
	private BigDecimal accountBalance;
	private String password;
	private boolean isActive = true;
}
