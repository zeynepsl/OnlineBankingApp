package patika.bootcamp.onlinebanking.service;

import java.util.List;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.customer.Customer;

public interface CustomerService {
	
	Customer create(Customer customer);
	Customer get(Long id) throws BaseException;
	void delete(Long id, Boolean hardDelete) throws BaseException;
	Customer update(Customer customer);
	
	List<Customer> getAll();
	
	Customer findByEmail(String email) throws BaseException;
	Customer findByIdentityNumber(String identityNumber) throws BaseException;
	Customer findByPhoneNumber(String phoneNumber) throws BaseException;
	
	//getBalance();
	//getBalanceByAccountType(AccountType type);
	
	List<Customer> findByAgeBetween(Integer startAge, Integer endAge);
	
	List<Customer> finAllActiveCustomers();
	List<Customer> finAllNotActiveCustomers();
	
	List<Customer> findByIsConfirmedByAdminTrue();
	List<Customer> findByIsConfirmedByAdminFalse();

	void activateCustomer(Long id) throws BaseException;
	void disableCustomer(Long id) throws BaseException;
	
	void confirmCustomer(Long id) throws BaseException;

}
