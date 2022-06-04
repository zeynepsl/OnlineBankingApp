package patika.bootcamp.onlinebanking.service.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.converter.bank.BranchConverter;
import patika.bootcamp.onlinebanking.dto.bank.BranchResponseDto;
import patika.bootcamp.onlinebanking.dto.bank.CreateBranchRequestDto;
import patika.bootcamp.onlinebanking.exception.BranchServiceOperationException;
import patika.bootcamp.onlinebanking.model.bank.Branch;
import patika.bootcamp.onlinebanking.service.BranchService;
import patika.bootcamp.onlinebanking.service.facade.BranchFacade;

@Service
@RequiredArgsConstructor
public class BranchFacadeImpl implements BranchFacade{
	private final BranchService bankBranchService;
	private final BranchConverter bankBranchConverter;

	@Override
	public ResponseEntity<BranchResponseDto> create(CreateBranchRequestDto createBankBranchRequestDto) {
		//neighborhood -> o mahalled e 1 tane varsa ekleme
		
		Branch bankBranch = bankBranchConverter.toBankBranch(createBankBranchRequestDto);
		
		//BranchAddress bankBranchAddress = new BranchAddress();
		//bankBranchAddress.setId(createBankBranchRequestDto.getBankBranchAddressId());
		boolean existsByNeighborhood = bankBranchService.existsByNeighborhood(bankBranch.getBankBranchAddress().getNeighborhood());
		if(existsByNeighborhood) {
			throw new BranchServiceOperationException.BankBranchAlreadyExists("bank branch already exists");
		}
		
		
		bankBranch = bankBranchService.create(bankBranch);
		return new ResponseEntity<>(bankBranchConverter.toBankBranchResponseDto(bankBranch), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<BranchResponseDto> update(Branch branch) {
		branch = bankBranchService.update(branch);
		return ResponseEntity.ok(bankBranchConverter.toBankBranchResponseDto(branch));
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		bankBranchService.delete(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<BranchResponseDto> get(Long id) {
		BranchResponseDto bankBranchResponseDto = bankBranchConverter.toBankBranchResponseDto(bankBranchService.get(id));
		return ResponseEntity.ok(bankBranchResponseDto);
	}

	@Override
	public ResponseEntity<List<BranchResponseDto>> getAll() {
		List<BranchResponseDto> bankBranchResponseDtos = toBankBranchResponseDtoList(bankBranchService.getAll());
		return ResponseEntity.ok(bankBranchResponseDtos);
	}

	public List<BranchResponseDto> toBankBranchResponseDtoList(List<Branch> bankBranchs) {
		List<BranchResponseDto> bankBranchResponseDtos = new ArrayList<BranchResponseDto>();
		bankBranchs.forEach(bankBranch -> {
			BranchResponseDto bankBranchResponseDto = bankBranchConverter.toBankBranchResponseDto(bankBranch);
			bankBranchResponseDtos.add(bankBranchResponseDto);
		});
		return bankBranchResponseDtos;
	}

	@Override
	public ResponseEntity<List<BranchResponseDto>> findByCountry(String country) {
		return ResponseEntity
				.ok(toBankBranchResponseDtoList(bankBranchService.findByCountry(country)));
	}

	@Override
	public ResponseEntity<List<BranchResponseDto>> findByCity(String city) {
		return ResponseEntity
				.ok(toBankBranchResponseDtoList(bankBranchService.findByCity(city)));
	}

	@Override
	public ResponseEntity<List<BranchResponseDto>> findByDistrict(String district) {
		return ResponseEntity
				.ok(toBankBranchResponseDtoList(bankBranchService.findByDistrict(district)));
	}

	@Override
	public ResponseEntity<BranchResponseDto> findByNeighborhood(String neighborhood) {
		BranchResponseDto bankBranchResponseDto = bankBranchConverter.toBankBranchResponseDto(
				bankBranchService.findByNeighborhood(neighborhood));
		return ResponseEntity.ok(bankBranchResponseDto);
	}
	
}
