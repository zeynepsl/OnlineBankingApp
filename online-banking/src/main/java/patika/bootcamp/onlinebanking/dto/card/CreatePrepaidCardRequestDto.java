package patika.bootcamp.onlinebanking.dto.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePrepaidCardRequestDto {
	private Long customerId;
	private String password;
}
