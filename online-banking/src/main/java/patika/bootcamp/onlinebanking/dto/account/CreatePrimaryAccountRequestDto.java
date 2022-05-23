package patika.bootcamp.onlinebanking.dto.account;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePrimaryAccountRequestDto {
	private Long currencyId;
	private Long customerId;
}
