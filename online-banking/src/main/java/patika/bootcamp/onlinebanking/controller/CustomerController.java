package patika.bootcamp.onlinebanking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.dto.request.CreateCustomerRequestDto;
import patika.bootcamp.onlinebanking.service.CustomerService;

@RestController
@RequestMapping("api/customers")
@RequiredArgsConstructor
public class CustomerController {
	private final CustomerService customerService;
	
	@PostMapping("/")
	public ResponseEntity<CustomerResponseDto> create(@RequestBody @Validated CreateCustomerRequestDto createCustomerRequestDto) {
		return new ResponseEntity<>(customerService.create(createCustomerRequestDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponseDto> get(@PathVariable Long id) {
		return ResponseEntity.ok(customerService.get(id));
	}
	
	@DeleteMapping("/{id}/{hardDelete}")
	public ResponseEntity<?> delete(@PathVariable Long id, @PathVariable Boolean hardDelete){
		customerService.delete(id, hardDelete);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/")
	public ResponseEntity<?> update(@RequestBody CreateCustomerRequestDto createCustomerRequestDto){
		customerService.update(createCustomerRequestDto);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CustomerResponseDto>> getAll(){
		return ResponseEntity.ok(customerService.getAll());
	}
	
	@GetMapping("/email")
	public ResponseEntity<CustomerResponseDto> findByEmail(@RequestParam String email){
		return ResponseEntity.ok(customerService.findByEmail(email));
	}
	
	@GetMapping("/identity")
	public ResponseEntity<CustomerResponseDto> findByIdentityNumber(@RequestParam String identityNumber){
		return ResponseEntity.ok(customerService.findByIdentityNumber(identityNumber));
	}
	
	@GetMapping("/phone")
	public ResponseEntity<CustomerResponseDto> findByPhoneNumber(@RequestParam String phoneNumber){
		return ResponseEntity.ok(customerService.findByPhoneNumber(phoneNumber));
	}
	
	@GetMapping("/{startAge}/{endAge}")
	public ResponseEntity<List<CustomerResponseDto>> findByAgeBetween(@PathVariable Integer startAge, @PathVariable Integer endAge){
		return ResponseEntity.ok(customerService.findByAgeBetween(startAge, endAge));
	}
	
	@GetMapping("/active")
	public ResponseEntity<List<CustomerResponseDto>> finAllActiveCustomers(){
		return ResponseEntity.ok(customerService.finAllActiveCustomers());
	}
	
	@GetMapping("/notactive")
	public ResponseEntity<List<CustomerResponseDto>> finAllNotActiveCustomers(){
		return ResponseEntity.ok(customerService.finAllNotActiveCustomers());
	}
	
	@GetMapping("/confirmed")
	public ResponseEntity<List<CustomerResponseDto>> findByIsConfirmedByAdminTrue(){
		return ResponseEntity.ok(customerService.findByIsConfirmedByAdminTrue());
	}
	
	@GetMapping("/unconfirmed")
	public ResponseEntity<List<CustomerResponseDto>> findByIsConfirmedByAdminFalse(){
		return ResponseEntity.ok(customerService.findByIsConfirmedByAdminFalse());
	}

	@PatchMapping("/activate/{id}")
	public ResponseEntity<?> activateCustomer(@PathVariable Long id){
		customerService.activateCustomer(id);
		return ResponseEntity.ok().build();
	}
	
	@PatchMapping("/disable/{id}")
	public ResponseEntity<?> disableCustomer(@PathVariable Long id){
		customerService.disableCustomer(id);
		return ResponseEntity.ok().build();
	}
	
	@PatchMapping("/confirm/{id}")
	public ResponseEntity<?> confirmCustomer(@PathVariable Long id){
		customerService.confirmCustomer(id);
		return ResponseEntity.ok().build();
	}

}
