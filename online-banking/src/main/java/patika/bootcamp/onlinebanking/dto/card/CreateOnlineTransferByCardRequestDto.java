package patika.bootcamp.onlinebanking.dto.card;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOnlineTransferByCardRequestDto {
	@NotBlank
	private String cardNo;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@NotNull
	private BigDecimal amount;
	
	@NotBlank
	private String cvv;
	
	@NotNull
	private Date dueDate;
	
	@NotBlank
	private String toAccountNumber;
}
