package patika.bootcamp.onlinebanking.dto.bank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchAddressResponseDto {
	private String country;
	private String city;
	private String district;
	private String neighborhood;
}
