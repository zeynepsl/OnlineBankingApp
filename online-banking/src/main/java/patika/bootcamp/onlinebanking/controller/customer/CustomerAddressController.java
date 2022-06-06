package patika.bootcamp.onlinebanking.controller.customer;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.customer.CreateCustomerAddressRequestDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerAddressResponseDto;
import patika.bootcamp.onlinebanking.model.customer.CustomerAddress;
import patika.bootcamp.onlinebanking.model.enums.AdressType;
import patika.bootcamp.onlinebanking.service.facade.CustomerAddressFacade;
import patika.bootcamp.onlinebanking.validator.Validator;

@RestController
@RequestMapping("api/customeraddresses")
@RequiredArgsConstructor
public class CustomerAddressController {
	private final CustomerAddressFacade customerAddressFacade;
	private final Validator<Long> idvalidator;
	
	@PostMapping("/")
	ResponseEntity<CustomerAddressResponseDto> create(@RequestBody CreateCustomerAddressRequestDto createCustomerAddressRequestDto){
		return customerAddressFacade.create(createCustomerAddressRequestDto);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<CustomerAddressResponseDto> get(@PathVariable Long id){
		idvalidator.validate(id);
		return customerAddressFacade.get(id);
	}
	
	@PutMapping("/")
	ResponseEntity<CustomerAddressResponseDto> update(@RequestBody CustomerAddress customerAddress){
		return customerAddressFacade.update(customerAddress);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<?> delete(@PathVariable Long id){
		idvalidator.validate(id);
		return customerAddressFacade.delete(id);
	}
	
	@GetMapping("/")
	ResponseEntity<List<CustomerAddressResponseDto>> getAll(){
		return customerAddressFacade.getAll();
	}
	
	@GetMapping("/customer/{customerId}")
	ResponseEntity<List<CustomerAddressResponseDto>> findByCustomer_Id(@PathVariable Long customerId){
		idvalidator.validate(customerId);
		return customerAddressFacade.findByCustomer_Id(customerId);
	}
	
	@GetMapping("/customer/{customerId}/addressType/{addressType}")
	ResponseEntity<List<CustomerAddressResponseDto>> findByCustomer_IdAndAdressType(@PathVariable Long customerId, @PathVariable AdressType addressType){
		idvalidator.validate(customerId);
		return customerAddressFacade.findByCustomer_IdAndAdressType(customerId, addressType);
	}
	
}
