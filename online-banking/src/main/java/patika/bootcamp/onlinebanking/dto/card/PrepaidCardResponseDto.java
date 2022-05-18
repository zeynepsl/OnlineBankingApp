package patika.bootcamp.onlinebanking.dto.card;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrepaidCardResponseDto {
	private String cardNo;
	private BigDecimal accountBalance;
	private Long customerId;
	private BigDecimal minBalance = BigDecimal.valueOf(300);
	private BigDecimal maxBalance = BigDecimal.valueOf(1000);
}
