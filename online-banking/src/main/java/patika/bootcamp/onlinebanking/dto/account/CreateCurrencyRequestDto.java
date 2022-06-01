package patika.bootcamp.onlinebanking.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCurrencyRequestDto {
	private String name;//Turkish lira
	private String code;//TRY 
	private String symbol;//₺
}
