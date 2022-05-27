package patika.bootcamp.onlinebanking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.request.CreateBankBranchRequestDto;
import patika.bootcamp.onlinebanking.dto.response.BankBranchResponseDto;
import patika.bootcamp.onlinebanking.service.facade.BankBranchFacade;

@RestController
@RequestMapping("api/bankbranches")
@RequiredArgsConstructor
public class BankBranchController {
	private final BankBranchFacade bankBranchFacade;
	
	@PostMapping("/")
	ResponseEntity<BankBranchResponseDto> create(@RequestBody CreateBankBranchRequestDto createBankBranchRequestDto){
		return bankBranchFacade.create(createBankBranchRequestDto);
	}
	
	@PutMapping("/")
	ResponseEntity<BankBranchResponseDto> update(@RequestBody CreateBankBranchRequestDto createBankBranchRequestDto){
		return bankBranchFacade.update(createBankBranchRequestDto);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<?> delete(@PathVariable Long id){
		return bankBranchFacade.delete(id);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<BankBranchResponseDto> get(@PathVariable Long id){
		return bankBranchFacade.get(id);
	}
	
	@GetMapping("/")
	ResponseEntity<List<BankBranchResponseDto>> getAll(){
		return bankBranchFacade.getAll();
	}
	
	@GetMapping("/country/{country}")
	ResponseEntity<List<BankBranchResponseDto>> findByCountry(@PathVariable String country){
		return bankBranchFacade.findByCountry(country);
	}

	@GetMapping("/city/{city}")
	ResponseEntity<List<BankBranchResponseDto>> findByCity(@PathVariable String city){
		return bankBranchFacade.findByCity(city);
	}
	
	@GetMapping("/district/{district}")
	ResponseEntity<List<BankBranchResponseDto>> findByDistrict(@PathVariable  String district){
		return bankBranchFacade.findByDistrict(district);
	}
	
	@GetMapping("/neighborhood/{neighborhood}")
	ResponseEntity<BankBranchResponseDto> findByNeighborhood(@PathVariable String neighborhood){
		return bankBranchFacade.findByNeighborhood(neighborhood);
	}
}
