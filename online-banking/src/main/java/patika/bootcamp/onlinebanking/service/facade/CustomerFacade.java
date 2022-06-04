package patika.bootcamp.onlinebanking.service.facade;

import java.util.List;

import org.springframework.http.ResponseEntity;

import patika.bootcamp.onlinebanking.dto.customer.CreateCustomerRequestDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.customer.Customer;

public interface CustomerFacade {
	ResponseEntity<List<CustomerResponseDto>> getAll();

	ResponseEntity<CustomerResponseDto> create(CreateCustomerRequestDto createCustomerRequestDto);

	ResponseEntity<CustomerResponseDto> get(Long id) throws BaseException;
	
	ResponseEntity<CustomerResponseDto> update(Customer customer);

	ResponseEntity<CustomerResponseDto> findByEmail(String email) throws BaseException;

	ResponseEntity<CustomerResponseDto> findByIdentityNumber(String identityNumber) throws BaseException;

	ResponseEntity<CustomerResponseDto> findByPhoneNumber(String phoneNumber) throws BaseException;

	ResponseEntity<List<CustomerResponseDto>> findByAgeBetween(Integer startAge, Integer endAge);

	ResponseEntity<List<CustomerResponseDto>> finAllActiveCustomers();

	ResponseEntity<List<CustomerResponseDto>> finAllNotActiveCustomers();

	ResponseEntity<List<CustomerResponseDto>> findByIsConfirmedByAdminTrue();

	ResponseEntity<List<CustomerResponseDto>> findByIsConfirmedByAdminFalse();

	ResponseEntity<?> delete(Long id, Boolean hardDelete);
	ResponseEntity<?> activateCustomer(Long id);
	ResponseEntity<?> disableCustomer(Long id);
	ResponseEntity<?> confirmCustomer(Long id);
}
