package patika.bootcamp.onlinebanking.controller.customer;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.customer.CreateCustomerRequestDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.service.facade.CustomerFacade;
import patika.bootcamp.onlinebanking.validator.Validator;

@RestController
@RequestMapping("api/customers")
@RequiredArgsConstructor
public class CustomerController {
	private final CustomerFacade customerFacade;
	private final Validator<Long> idValidator;
	
	@PostMapping("/")
	public ResponseEntity<CustomerResponseDto> create(@RequestBody @Validated CreateCustomerRequestDto createCustomerRequestDto) {
		return customerFacade.create(createCustomerRequestDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponseDto> get(@PathVariable Long id) {
		idValidator.validate(id);
		return customerFacade.get(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id, @RequestParam Boolean hardDelete){
		idValidator.validate(id);
		customerFacade.delete(id, hardDelete);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/")
	public ResponseEntity<?> update(@RequestBody Customer customer){
		return customerFacade.update(customer);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CustomerResponseDto>> getAll(){
		return customerFacade.getAll();
	}
	
	@GetMapping("/email")
	public ResponseEntity<CustomerResponseDto> findByEmail(@RequestParam String email){
		return customerFacade.findByEmail(email);
	}
	
	@GetMapping("/identity")
	public ResponseEntity<CustomerResponseDto> findByIdentityNumber(@RequestParam String identityNumber){
		return customerFacade.findByIdentityNumber(identityNumber);
	}
	
	@GetMapping("/phone")
	public ResponseEntity<CustomerResponseDto> findByPhoneNumber(@RequestParam String phoneNumber){
		return customerFacade.findByPhoneNumber(phoneNumber);
	}
	
	@GetMapping("/{startAge}/{endAge}")
	public ResponseEntity<List<CustomerResponseDto>> findByAgeBetween(@PathVariable Integer startAge, @PathVariable Integer endAge){
		return customerFacade.findByAgeBetween(startAge, endAge);
	}
	
	@GetMapping("/active")
	public ResponseEntity<List<CustomerResponseDto>> finAllActiveCustomers(){
		return customerFacade.finAllActiveCustomers();
	}
	
	@GetMapping("/notactive")
	public ResponseEntity<List<CustomerResponseDto>> finAllNotActiveCustomers(){
		return customerFacade.finAllNotActiveCustomers();
	}
	
	@GetMapping("/confirmed")
	public ResponseEntity<List<CustomerResponseDto>> findByIsConfirmedByAdminTrue(){
		return customerFacade.findByIsConfirmedByAdminTrue();
	}
	
	@GetMapping("/unconfirmed")
	public ResponseEntity<List<CustomerResponseDto>> findByIsConfirmedByAdminFalse(){
		return customerFacade.findByIsConfirmedByAdminFalse();
	}

	@PatchMapping("/activate/{id}")
	public ResponseEntity<?> activateCustomer(@PathVariable Long id){
		idValidator.validate(id);
		customerFacade.activateCustomer(id);
		return ResponseEntity.ok().build();
	}
	
	@PatchMapping("/disable/{id}")
	public ResponseEntity<?> disableCustomer(@PathVariable Long id){
		idValidator.validate(id);
		customerFacade.disableCustomer(id);
		return ResponseEntity.ok().build();
	}
	
	@PatchMapping("/confirm/{id}")
	public ResponseEntity<?> confirmCustomer(@PathVariable Long id){
		idValidator.validate(id);
		customerFacade.confirmCustomer(id);
		return ResponseEntity.ok().build();
	}

}
