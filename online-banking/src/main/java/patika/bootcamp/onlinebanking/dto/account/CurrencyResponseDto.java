package patika.bootcamp.onlinebanking.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyResponseDto {
	private Long id;
	private String name;
	private String code;
	private String symbol;
}
