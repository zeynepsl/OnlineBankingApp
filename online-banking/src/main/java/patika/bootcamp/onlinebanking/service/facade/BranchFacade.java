package patika.bootcamp.onlinebanking.service.facade;

import java.util.List;

import org.springframework.http.ResponseEntity;

import patika.bootcamp.onlinebanking.dto.bank.BranchResponseDto;
import patika.bootcamp.onlinebanking.dto.bank.CreateBranchRequestDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.bank.Branch;

public interface BranchFacade {
	ResponseEntity<BranchResponseDto> create(CreateBranchRequestDto createBankBranchRequestDto) throws BaseException;
	ResponseEntity<BranchResponseDto> update(Branch branch);
	ResponseEntity<?> delete(Long id);
	ResponseEntity<BranchResponseDto> get(Long id);
	
	ResponseEntity<List<BranchResponseDto>> getAll();
	ResponseEntity<List<BranchResponseDto>> findByCountry(String country);
	ResponseEntity<List<BranchResponseDto>> findByCity(String city);
	ResponseEntity<List<BranchResponseDto>> findByDistrict(String district);
	ResponseEntity<BranchResponseDto> findByNeighborhood(String neighborhood);
}
