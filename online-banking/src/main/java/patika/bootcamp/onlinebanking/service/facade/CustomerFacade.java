package patika.bootcamp.onlinebanking.service.facade;

import java.util.List;

import org.springframework.http.ResponseEntity;

import patika.bootcamp.onlinebanking.dto.request.CreateCustomerRequestDto;
import patika.bootcamp.onlinebanking.dto.response.CustomerResponseDto;
import patika.bootcamp.onlinebanking.exception.BaseException;

public interface CustomerFacade {
	ResponseEntity<List<CustomerResponseDto>> getAll();

	ResponseEntity<CustomerResponseDto> create(CreateCustomerRequestDto createCustomerRequestDto);

	ResponseEntity<CustomerResponseDto> get(Long id) throws BaseException;

	ResponseEntity<CustomerResponseDto> update(CreateCustomerRequestDto createCustomerRequestDto);

	ResponseEntity<CustomerResponseDto> findByEmail(String email) throws BaseException;

	ResponseEntity<CustomerResponseDto> findByIdentityNumber(String identityNumber) throws BaseException;

	ResponseEntity<CustomerResponseDto> findByPhoneNumber(String phoneNumber) throws BaseException;

	// getBalance();
	// getBalanceByAccountType(AccountType type);

	ResponseEntity<List<CustomerResponseDto>> findByAgeBetween(Integer startAge, Integer endAge);

	ResponseEntity<List<CustomerResponseDto>> finAllActiveCustomers();

	ResponseEntity<List<CustomerResponseDto>> finAllNotActiveCustomers();

	ResponseEntity<List<CustomerResponseDto>> findByIsConfirmedByAdminTrue();

	ResponseEntity<List<CustomerResponseDto>> findByIsConfirmedByAdminFalse();

	/*
	 * void delete(Long id, Boolean hardDelete) throws BaseException; void
	 * activateCustomer(Long id) throws BaseException; void disableCustomer(Long id)
	 * throws BaseException;
	 * 
	 * void confirmCustomer(Long id) throws BaseException;
	 */
}
