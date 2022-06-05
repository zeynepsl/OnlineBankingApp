package patika.bootcamp.onlinebanking.dto.card;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCreditCardRequestDto {
	@NotNull
	private Long customerId;
	
	@NotNull
	private Long bankBranchId;
	
	@NotNull
	private BigDecimal cardLimit;
	
	@NotBlank
	private String password;
}
