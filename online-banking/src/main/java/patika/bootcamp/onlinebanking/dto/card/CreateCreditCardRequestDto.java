package patika.bootcamp.onlinebanking.dto.card;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCreditCardRequestDto {
	private Long customerId;
	private Long bankBranchId;
	private BigDecimal cardLimit;
	private String password;
}
