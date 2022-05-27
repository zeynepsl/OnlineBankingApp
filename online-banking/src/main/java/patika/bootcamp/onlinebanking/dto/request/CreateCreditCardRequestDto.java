package patika.bootcamp.onlinebanking.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCreditCardRequestDto {
	private Long customerId;
	private Long primaryAccountId;
}
