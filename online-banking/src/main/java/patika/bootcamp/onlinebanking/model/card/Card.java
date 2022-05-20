package patika.bootcamp.onlinebanking.model.card;

import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.BaseExtendedModel;

@Getter
@Setter
@MappedSuperclass
public abstract class Card extends BaseExtendedModel{
	private String cardNo;
	private BigDecimal accountBalance;
	private boolean isActive = true;
}
