package patika.bootcamp.onlinebanking.converter;

import org.springframework.stereotype.Component;

import patika.bootcamp.onlinebanking.dto.response.BankBranchAddressResponseDto;
import patika.bootcamp.onlinebanking.model.bank.BankBranchAddress;

@Component
public class BankBranchAddressConverter {

	public BankBranchAddressResponseDto toBankBranchAddressResponseDto(BankBranchAddress bankBranchAddress) {
		BankBranchAddressResponseDto addressResponseDto = new BankBranchAddressResponseDto();
		addressResponseDto.setCountry(bankBranchAddress.getCountry());
		addressResponseDto.setCity(bankBranchAddress.getCity());
		addressResponseDto.setDistrict(bankBranchAddress.getDistrict());
		addressResponseDto.setNeighborhood(bankBranchAddress.getNeighborhood());
		addressResponseDto.setAdressDescription(bankBranchAddress.getAdressDescription());
		return addressResponseDto;
	}

}
