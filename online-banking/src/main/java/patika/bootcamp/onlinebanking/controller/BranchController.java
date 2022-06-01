package patika.bootcamp.onlinebanking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.bank.BranchResponseDto;
import patika.bootcamp.onlinebanking.dto.bank.CreateBranchRequestDto;
import patika.bootcamp.onlinebanking.service.facade.BranchFacade;

@RestController
@RequestMapping("api/bankbranches")
@RequiredArgsConstructor
public class BranchController {
	private final BranchFacade bankBranchFacade;
	
	@PostMapping("/")
	ResponseEntity<BranchResponseDto> create(@RequestBody CreateBranchRequestDto createBankBranchRequestDto){
		return bankBranchFacade.create(createBankBranchRequestDto);
	}
	
	@PutMapping("/")
	ResponseEntity<BranchResponseDto> update(@RequestBody CreateBranchRequestDto createBankBranchRequestDto){
		return bankBranchFacade.update(createBankBranchRequestDto);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<?> delete(@PathVariable Long id){
		return bankBranchFacade.delete(id);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<BranchResponseDto> get(@PathVariable Long id){
		return bankBranchFacade.get(id);
	}
	
	@GetMapping("/")
	ResponseEntity<List<BranchResponseDto>> getAll(){
		return bankBranchFacade.getAll();
	}
	
	@GetMapping("/country/{country}")
	ResponseEntity<List<BranchResponseDto>> findByCountry(@PathVariable String country){
		return bankBranchFacade.findByCountry(country);
	}

	@GetMapping("/city/{city}")
	ResponseEntity<List<BranchResponseDto>> findByCity(@PathVariable String city){
		return bankBranchFacade.findByCity(city);
	}
	
	@GetMapping("/district/{district}")
	ResponseEntity<List<BranchResponseDto>> findByDistrict(@PathVariable  String district){
		return bankBranchFacade.findByDistrict(district);
	}
	
	@GetMapping("/neighborhood/{neighborhood}")
	ResponseEntity<BranchResponseDto> findByNeighborhood(@PathVariable String neighborhood){
		return bankBranchFacade.findByNeighborhood(neighborhood);
	}
}
