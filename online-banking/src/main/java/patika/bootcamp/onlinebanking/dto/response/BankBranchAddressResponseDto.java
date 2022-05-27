package patika.bootcamp.onlinebanking.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankBranchAddressResponseDto {
	private String country;
	private String city;
	private String district;
	private String neighborhood;//mahalle
	private String adressDescription;
}
