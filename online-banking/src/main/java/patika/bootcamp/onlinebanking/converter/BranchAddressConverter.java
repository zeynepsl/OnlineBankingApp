package patika.bootcamp.onlinebanking.converter;

import org.springframework.stereotype.Component;

import patika.bootcamp.onlinebanking.dto.bank.BranchAddressResponseDto;
import patika.bootcamp.onlinebanking.dto.bank.CreateBranchAddressRequestDto;
import patika.bootcamp.onlinebanking.model.bank.BranchAddress;

@Component
public class BranchAddressConverter {

	public BranchAddressResponseDto toBankBranchAddressResponseDto(BranchAddress bankBranchAddress) {
		BranchAddressResponseDto addressResponseDto = new BranchAddressResponseDto();
		addressResponseDto.setCountry(bankBranchAddress.getCountry());
		addressResponseDto.setCity(bankBranchAddress.getCity());
		addressResponseDto.setDistrict(bankBranchAddress.getDistrict());
		addressResponseDto.setNeighborhood(bankBranchAddress.getNeighborhood());
		addressResponseDto.setAdressDescription(bankBranchAddress.getAdressDescription());
		return addressResponseDto;
	}

	public BranchAddress toBranchAddress(CreateBranchAddressRequestDto createBranchAddressRequestDto) {
		BranchAddress branchAddress = new BranchAddress();
		branchAddress.setAdressDescription(createBranchAddressRequestDto.getAdressDescription());
		branchAddress.setCity(createBranchAddressRequestDto.getCity());
		branchAddress.setCountry(createBranchAddressRequestDto.getCountry());
		branchAddress.setDistrict(createBranchAddressRequestDto.getDistrict());
		branchAddress.setNeighborhood(createBranchAddressRequestDto.getNeighborhood());
		return branchAddress;
	}

}
