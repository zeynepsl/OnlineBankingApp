package patika.bootcamp.onlinebanking.dto.bank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBranchAddressRequestDto {
	private String country;
	private String city;
	private String district;
	private String neighborhood;//mahalle
	private String adressDescription;//cadde ismi, no vs..

}
