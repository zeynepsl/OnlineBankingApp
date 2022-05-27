package patika.bootcamp.onlinebanking.service.facade;

import java.util.List;

import org.springframework.http.ResponseEntity;

import patika.bootcamp.onlinebanking.dto.request.CreateBankBranchRequestDto;
import patika.bootcamp.onlinebanking.dto.response.BankBranchResponseDto;
import patika.bootcamp.onlinebanking.exception.BaseException;

public interface BankBranchFacade {
	ResponseEntity<BankBranchResponseDto> create(CreateBankBranchRequestDto createBankBranchRequestDto) throws BaseException;
	ResponseEntity<BankBranchResponseDto> update(CreateBankBranchRequestDto createBankBranchRequestDto);
	ResponseEntity<?> delete(Long id);
	ResponseEntity<BankBranchResponseDto> get(Long id);
	
	ResponseEntity<List<BankBranchResponseDto>> getAll();
	ResponseEntity<List<BankBranchResponseDto>> findByCountry(String country);
	ResponseEntity<List<BankBranchResponseDto>> findByCity(String city);
	ResponseEntity<List<BankBranchResponseDto>> findByDistrict(String district);
	ResponseEntity<BankBranchResponseDto> findByNeighborhood(String neighborhood);
}
