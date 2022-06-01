package patika.bootcamp.onlinebanking.dto.card;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOnlineTransferByCardRequestDto {
	private String cardNo;
	private String firstName;
	private String lastName;
	private BigDecimal amount;
	private String cvv;
	private Date dueDate;
	private String to;
}
