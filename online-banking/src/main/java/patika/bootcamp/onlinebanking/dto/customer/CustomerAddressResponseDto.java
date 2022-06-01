package patika.bootcamp.onlinebanking.dto.customer;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.enums.AdressType;

@Getter
@Setter
public class CustomerAddressResponseDto {

	private CustomerResponseDto customerResponseDto;
	private AdressType addressType;
	private String name;
	private String country;
	private String city;
	private String district;
	private String neighborhood;
	private String adressDescription;
}
