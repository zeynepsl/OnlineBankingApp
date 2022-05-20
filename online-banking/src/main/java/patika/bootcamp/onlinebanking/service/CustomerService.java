package patika.bootcamp.onlinebanking.service;

import java.util.List;
import java.util.Optional;

import patika.bootcamp.onlinebanking.dto.customer.CreateCustomerRequestDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.exception.BaseException;

public interface CustomerService {
	
	void create(CreateCustomerRequestDto createCustomerRequestDto);
	CustomerResponseDto get(Long id) throws BaseException;
	void delete(Long id, Boolean deleteIsHard) throws BaseException;
	void update(CreateCustomerRequestDto createCustomerRequestDto);
	
	List<CustomerResponseDto> getAll();
	
	CustomerResponseDto findByEmail(String email) throws BaseException;
	CustomerResponseDto findByIdentityNumber(String identityNumber) throws BaseException;
	CustomerResponseDto findByPhoneNumber(String phoneNumber) throws BaseException;
	
	List<CustomerResponseDto> findByAgeBetween(Integer startAge, Integer endAge);
	
	List<CustomerResponseDto> finAllActiveCustomers();
	List<CustomerResponseDto> finAllNotActiveCustomers();
	
	List<CustomerResponseDto> findByIsConfirmedByAdminTrue();
	List<CustomerResponseDto> findByIsConfirmedByAdminFalse();

	void activateCustomer(Long id) throws BaseException;
	void disableCustomer(Long id) throws BaseException;
	
	void confirmCustomer(Long id) throws BaseException;

}
