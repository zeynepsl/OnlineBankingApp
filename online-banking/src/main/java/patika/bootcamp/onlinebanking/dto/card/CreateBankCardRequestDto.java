package patika.bootcamp.onlinebanking.dto.card;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBankCardRequestDto {
	@NotNull
	private String password;
	
	@NotNull
	private Long customerId;
	
	@NotNull
	private Long accountId;
}
