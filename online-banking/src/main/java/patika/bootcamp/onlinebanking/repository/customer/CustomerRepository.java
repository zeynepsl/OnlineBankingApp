package patika.bootcamp.onlinebanking.repository.customer;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import patika.bootcamp.onlinebanking.model.customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	Optional<Customer> findByContactInformation_PrimaryEmail(String email);
	Optional<Customer> findByIdentityNumber(String identityNumber);
	Optional<Customer> findByContactInformation_PrimaryPhoneNumber(String phoneNumber);
	
	List<Customer> findByBirthDate(Date birthDate);
	List<Customer> findByBirthDateAfter(Date birthDate);
	List<Customer> findByBirthDateBefore(Date birthDate);
	
	List<Customer> findByAgeBetween(Integer startAge, Integer endAge);
	
	List<Customer> findByIsActiveTrue();
	List<Customer> findByIsActiveFalse();
	
	List<Customer> findByIsConfirmedByAdminTrue();
	List<Customer> findByIsConfirmedByAdminFalse();
}
