package patika.bootcamp.onlinebanking.converter.bank;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.bank.BranchAddressResponseDto;
import patika.bootcamp.onlinebanking.dto.bank.BranchResponseDto;
import patika.bootcamp.onlinebanking.dto.bank.CreateBranchRequestDto;
import patika.bootcamp.onlinebanking.model.bank.Branch;
import patika.bootcamp.onlinebanking.model.bank.BranchAddress;

@Component
@RequiredArgsConstructor
public class BranchConverter {

	private final BranchAddressConverter branchAddressConverter;
	
	public Branch toBankBranch(CreateBranchRequestDto createBankBranchRequestDto) {
		Branch bankBranch = new Branch();
		bankBranch.setBranchCode(createBankBranchRequestDto.getBranchCode());
		bankBranch.setBranchName(createBankBranchRequestDto.getBranchName());
		
		BranchAddress branchAddress = new BranchAddress();
		branchAddress.setCity(createBankBranchRequestDto.getCreateBranchAddressRequestDto().getCity());
		branchAddress.setCountry(createBankBranchRequestDto.getCreateBranchAddressRequestDto().getCountry());
		branchAddress.setDistrict(createBankBranchRequestDto.getCreateBranchAddressRequestDto().getDistrict());
		branchAddress.setNeighborhood(createBankBranchRequestDto.getCreateBranchAddressRequestDto().getNeighborhood());
		
		bankBranch.setBankBranchAddress(branchAddress);
		
		return bankBranch;
	}

	public BranchResponseDto toBankBranchResponseDto(Branch bankBranch) {
		BranchResponseDto bankBranchResponseDto = new BranchResponseDto();
		bankBranchResponseDto.setId(bankBranch.getId());
		BranchAddressResponseDto addressResponseDto = branchAddressConverter
				.toBankBranchAddressResponseDto(bankBranch.getBankBranchAddress());
		bankBranchResponseDto.setBankBranchAddressResponseDto(addressResponseDto);
		
		bankBranchResponseDto.setBranchCode(bankBranch.getBranchCode());
		bankBranchResponseDto.setBranchName(bankBranch.getBranchName());
		
		return bankBranchResponseDto;
	}

}
