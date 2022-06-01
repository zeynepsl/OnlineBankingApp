package patika.bootcamp.onlinebanking.dto.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBankCardRequestDto {
	private String password;
	private Long customerId;
	private Long accountId;
}
