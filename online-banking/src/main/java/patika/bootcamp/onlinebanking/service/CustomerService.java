package patika.bootcamp.onlinebanking.service;

import java.util.List;
import java.util.Optional;

import patika.bootcamp.onlinebanking.dto.customer.CreateCustomerRequestDto;
import patika.bootcamp.onlinebanking.model.customer.Customer;

public interface CustomerService {
	
	void create(CreateCustomerRequestDto createCustomerRequestDto);
	Customer get(Long id);
	void delete(Long id);
	void update(Customer customer);
	List<Customer> getAll();
	
	Optional<Customer> findByEmail(String email);
	Optional<Customer> findByIdentityNumber(String identityNumber);
	Optional<Customer> findByPhoneNumber(String phoneNumber);
	
	List<Customer> findByAgeBetween(Integer startAge, Integer endAge);
	
	List<Customer> findByIsActiveTrue();
	List<Customer> findByIsActiveFalse();
	
	List<Customer> findByIsConfirmedByAdminTrue();
	List<Customer> findByIsConfirmedByAdminFalse();
}
