package patika.bootcamp.onlinebanking.converter;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.request.CreateBankBranchRequestDto;
import patika.bootcamp.onlinebanking.dto.response.BankBranchAddressResponseDto;
import patika.bootcamp.onlinebanking.dto.response.BankBranchResponseDto;
import patika.bootcamp.onlinebanking.model.bank.BankBranch;
import patika.bootcamp.onlinebanking.model.bank.BankBranchAddress;

@Component
@RequiredArgsConstructor
public class BankBranchConverter {

	private final BankBranchAddressConverter bankBranchAddressConverter;
	
	public BankBranch toBankBranch(CreateBankBranchRequestDto createBankBranchRequestDto) {
		BankBranch bankBranch = new BankBranch();
		bankBranch.setBranchCode(createBankBranchRequestDto.getBranchCode());
		bankBranch.setBranchName(createBankBranchRequestDto.getBranchName());
		
		BankBranchAddress bankBranchAddress = new BankBranchAddress();
		bankBranchAddress.setId(createBankBranchRequestDto.getBankBranchAddressId());
		bankBranch.setBankBranchAddress(bankBranchAddress);
		
		return bankBranch;
	}

	public BankBranchResponseDto toBankBranchResponseDto(BankBranch bankBranch) {
		BankBranchResponseDto bankBranchResponseDto = new BankBranchResponseDto();
		
		BankBranchAddressResponseDto addressResponseDto =  bankBranchAddressConverter
				.toBankBranchAddressResponseDto(bankBranch.getBankBranchAddress());
		bankBranchResponseDto.setBankBranchAddressResponseDto(addressResponseDto);
		
		bankBranchResponseDto.setBranchCode(bankBranch.getBranchCode());
		bankBranchResponseDto.setBranchName(bankBranch.getBranchName());
		
		return bankBranchResponseDto;
	}

}
