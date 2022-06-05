package patika.bootcamp.onlinebanking.dto.customer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.enums.AdressType;

@Getter
@Setter
public class CreateCustomerAddressRequestDto {
	
	@NotNull
	private Long customerId;
	
	@NotNull
	private AdressType addressType;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String country;
	
	@NotBlank
	private String city;
	
	@NotBlank
	private String district;
	
	@NotBlank
	private String neighborhood;
	
	@NotBlank
	private String adressDescription;
}
