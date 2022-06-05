package patika.bootcamp.onlinebanking.dto.account;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCurrencyRequestDto {
	@NotBlank
	private String name;//Turkish lira
	
	@NotBlank
	private String code;//TRY
	
	@NotBlank
	private String symbol;//₺
}
