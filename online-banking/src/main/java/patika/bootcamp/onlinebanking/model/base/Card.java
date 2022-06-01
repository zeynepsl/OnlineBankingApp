package patika.bootcamp.onlinebanking.model.base;

import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Card extends BaseExtendedModel{
	private String cardNumber;
	private String password;
	private Boolean isActive = true;
}
