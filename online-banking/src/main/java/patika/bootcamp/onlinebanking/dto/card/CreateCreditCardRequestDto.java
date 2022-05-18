package patika.bootcamp.onlinebanking.dto.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCreditCardRequestDto {
	private Long customerId;
	private Long primaryAccountId;
}
