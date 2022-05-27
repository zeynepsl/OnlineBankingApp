package patika.bootcamp.onlinebanking.service.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.converter.BankBranchConverter;
import patika.bootcamp.onlinebanking.dto.request.CreateBankBranchRequestDto;
import patika.bootcamp.onlinebanking.dto.response.BankBranchResponseDto;
import patika.bootcamp.onlinebanking.exception.BankBranchServiceOperationException;
import patika.bootcamp.onlinebanking.model.bank.BankBranch;
import patika.bootcamp.onlinebanking.model.bank.BankBranchAddress;
import patika.bootcamp.onlinebanking.service.BankBranchService;
import patika.bootcamp.onlinebanking.service.facade.BankBranchFacade;

@Service
@RequiredArgsConstructor
public class BankBranchFacadeImpl implements BankBranchFacade{
	private final BankBranchService bankBranchService;
	private final BankBranchConverter bankBranchConverter;

	@Override
	public ResponseEntity<BankBranchResponseDto> create(CreateBankBranchRequestDto createBankBranchRequestDto) {
		//neighborhood -> o mahalled e 1 tane varsa ekleme
		BankBranchAddress bankBranchAddress = new BankBranchAddress();
		bankBranchAddress.setId(createBankBranchRequestDto.getBankBranchAddressId());
		boolean existsByNeighborhood = bankBranchService.existsByNeighborhood(bankBranchAddress.getNeighborhood());
		if(existsByNeighborhood) {
			throw new BankBranchServiceOperationException.BankBranchAlreadyExists("bank branch already exists");
		}
		
		BankBranch bankBranch = bankBranchConverter.toBankBranch(createBankBranchRequestDto);
		bankBranch = bankBranchService.create(bankBranch);
		return new ResponseEntity<>(bankBranchConverter.toBankBranchResponseDto(bankBranch), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<BankBranchResponseDto> update(CreateBankBranchRequestDto createBankBranchRequestDto) {
		BankBranch bankBranch = bankBranchConverter.toBankBranch(createBankBranchRequestDto);
		bankBranch = bankBranchService.update(bankBranch);
		return ResponseEntity.ok(bankBranchConverter.toBankBranchResponseDto(bankBranch));
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		bankBranchService.delete(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<BankBranchResponseDto> get(Long id) {
		BankBranchResponseDto bankBranchResponseDto = bankBranchConverter.toBankBranchResponseDto(bankBranchService.get(id));
		return ResponseEntity.ok(bankBranchResponseDto);
	}

	@Override
	public ResponseEntity<List<BankBranchResponseDto>> getAll() {
		List<BankBranchResponseDto> bankBranchResponseDtos = toBankBranchResponseDtoList(bankBranchService.getAll());
		return ResponseEntity.ok(bankBranchResponseDtos);
	}

	public List<BankBranchResponseDto> toBankBranchResponseDtoList(List<BankBranch> bankBranchs) {
		List<BankBranchResponseDto> bankBranchResponseDtos = new ArrayList<BankBranchResponseDto>();
		bankBranchs.forEach(bankBranch -> {
			BankBranchResponseDto bankBranchResponseDto = bankBranchConverter.toBankBranchResponseDto(bankBranch);
			bankBranchResponseDtos.add(bankBranchResponseDto);
		});
		return bankBranchResponseDtos;
	}

	@Override
	public ResponseEntity<List<BankBranchResponseDto>> findByCountry(String country) {
		return ResponseEntity
				.ok(toBankBranchResponseDtoList(bankBranchService.findByCountry(country)));
	}

	@Override
	public ResponseEntity<List<BankBranchResponseDto>> findByCity(String city) {
		return ResponseEntity
				.ok(toBankBranchResponseDtoList(bankBranchService.findByCity(city)));
	}

	@Override
	public ResponseEntity<List<BankBranchResponseDto>> findByDistrict(String district) {
		return ResponseEntity
				.ok(toBankBranchResponseDtoList(bankBranchService.findByDistrict(district)));
	}

	@Override
	public ResponseEntity<BankBranchResponseDto> findByNeighborhood(String neighborhood) {
		BankBranchResponseDto bankBranchResponseDto = bankBranchConverter.toBankBranchResponseDto(
				bankBranchService.findByNeighborhood(neighborhood));
		return ResponseEntity.ok(bankBranchResponseDto);
	}
	
}
