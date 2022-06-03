package patika.bootcamp.onlinebanking.service.facade;

import java.util.List;

import org.springframework.http.ResponseEntity;

import patika.bootcamp.onlinebanking.dto.customer.CreateCustomerAddressRequestDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerAddressResponseDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.customer.CustomerAddress;
import patika.bootcamp.onlinebanking.model.enums.AdressType;

public interface CustomerAddressFacade {
	
	ResponseEntity<CustomerAddressResponseDto> create(CreateCustomerAddressRequestDto createCustomerAddressRequestDto) throws BaseException;
	ResponseEntity<CustomerAddressResponseDto> get(Long id) throws BaseException;
	ResponseEntity<CustomerAddressResponseDto> update(CustomerAddress customerAddress);
	ResponseEntity<?> delete(Long id) throws BaseException;
	ResponseEntity<List<CustomerAddressResponseDto>> getAll();
	ResponseEntity<List<CustomerAddressResponseDto>> findByCustomer_Id(Long customerId);
	ResponseEntity<List<CustomerAddressResponseDto>> findByCustomer_IdAndAdressType(Long customerId,AdressType addressType);
}
