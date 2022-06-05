package patika.bootcamp.onlinebanking.dto.bank;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBranchAddressRequestDto {
	@NotBlank
	private String country;
	
	@NotBlank
	private String city;
	
	@NotBlank
	private String district;
	
	@NotBlank
	private String neighborhood;

}
