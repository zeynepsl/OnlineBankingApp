package patika.bootcamp.onlinebanking.controller.bank;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.bank.BranchResponseDto;
import patika.bootcamp.onlinebanking.dto.bank.CreateBranchRequestDto;
import patika.bootcamp.onlinebanking.model.bank.Branch;
import patika.bootcamp.onlinebanking.service.facade.BranchFacade;

@RestController
@RequestMapping("api/bankbranches")
@RequiredArgsConstructor
public class BranchController {
	private final BranchFacade bankBranchFacade;
	
	@PostMapping("/")
	public ResponseEntity<BranchResponseDto> create(@RequestBody CreateBranchRequestDto createBankBranchRequestDto){
		return bankBranchFacade.create(createBankBranchRequestDto);
	}
	
	@PutMapping("/")
	public ResponseEntity<BranchResponseDto> update(@RequestBody Branch branch){
		return bankBranchFacade.update(branch);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		return bankBranchFacade.delete(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BranchResponseDto> get(@PathVariable Long id){
		return bankBranchFacade.get(id);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<BranchResponseDto>> getAll(){
		return bankBranchFacade.getAll();
	}
	
	@GetMapping("/country/{country}")
	public ResponseEntity<List<BranchResponseDto>> findByCountry(@PathVariable String country){
		return bankBranchFacade.findByCountry(country);
	}

	@GetMapping("/city/{city}")
	public ResponseEntity<List<BranchResponseDto>> findByCity(@PathVariable String city){
		return bankBranchFacade.findByCity(city);
	}
	
	@GetMapping("/district/{district}")
	public ResponseEntity<List<BranchResponseDto>> findByDistrict(@PathVariable  String district){
		return bankBranchFacade.findByDistrict(district);
	}
	
	@GetMapping("/neighborhood/{neighborhood}")
	public ResponseEntity<BranchResponseDto> findByNeighborhood(@PathVariable String neighborhood){
		return bankBranchFacade.findByNeighborhood(neighborhood);
	}
}
