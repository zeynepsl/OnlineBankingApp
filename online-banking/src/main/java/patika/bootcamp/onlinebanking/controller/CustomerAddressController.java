package patika.bootcamp.onlinebanking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.customer.CreateCustomerAddressRequestDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerAddressResponseDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.enums.AdressType;
import patika.bootcamp.onlinebanking.service.facade.CustomerAddressFacade;

@RestController
@RequestMapping("api/customeraddresses")
@RequiredArgsConstructor
public class CustomerAddressController {
	private final CustomerAddressFacade customerAddressFacade;
	
	@PostMapping("/")
	ResponseEntity<CustomerAddressResponseDto> create(@RequestBody CreateCustomerAddressRequestDto createCustomerAddressRequestDto){
		return customerAddressFacade.create(createCustomerAddressRequestDto);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<CustomerAddressResponseDto> get(@PathVariable Long id){
		return customerAddressFacade.get(id);
	}
	
	@PutMapping("/")
	ResponseEntity<CustomerAddressResponseDto> update(@RequestBody CreateCustomerAddressRequestDto createCustomerAddressRequestDto){
		return customerAddressFacade.update(createCustomerAddressRequestDto);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<?> delete(@PathVariable Long id){
		return customerAddressFacade.delete(id);
	}
	
	@GetMapping("/")
	ResponseEntity<List<CustomerAddressResponseDto>> getAll(){
		return customerAddressFacade.getAll();
	}
	
	@GetMapping("/customer/{customerId}")
	ResponseEntity<List<CustomerAddressResponseDto>> findByCustomer_Id(@PathVariable Long customerId){
		return customerAddressFacade.findByCustomer_Id(customerId);
	}
	
	@GetMapping("/customer/{customerId}/addressType/{addressType}")
	ResponseEntity<List<CustomerAddressResponseDto>> findByCustomer_IdAndAdressType(@PathVariable Long customerId, @PathVariable AdressType addressType){
		return customerAddressFacade.findByCustomer_IdAndAdressType(customerId, addressType);
	}
	
}
