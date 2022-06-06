package patika.bootcamp.onlinebanking;

import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import patika.bootcamp.onlinebanking.model.customer.ContactInformation;
import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.model.customer.CustomerAddress;
import patika.bootcamp.onlinebanking.repository.customer.CustomerRepository;
import patika.bootcamp.onlinebanking.service.CustomerService;
import patika.bootcamp.onlinebanking.util.generate.CustomerNumberGenerator;

@SpringBootTest
public class CustomerTests {
	
	@Autowired
	public CustomerRepository customerRepository;
	
	@Autowired
	public CustomerService customerService;
	
	@Test
	void should_create_success_customer() {
		Customer customer = new Customer();
		customer.setAge(21);
		customer.setCustomerNumber(CustomerNumberGenerator.generate());
		
		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setCity("samsun");
		customerAddress.setCountry("turkiye");
		customerAddress.setDistrict("binevler");
		customerAddress.setNeighborhood("mimar sinan");
		customerAddress.setCustomer(customer);
		customer.setCustomerAddresses(Set.of(customerAddress));
		customer.setFirstName("zeynep");
		customer.setLastName("salman");
		
		ContactInformation contactInformation = new ContactInformation();
		contactInformation.setPrimaryEmail("zeynep@salman.com");
		contactInformation.setPrimaryPhoneNumber("+905511111111");
		contactInformation.setSecondaryEmail("");
		contactInformation.setSecondaryPhoneNumber("");
		customer.setContactInformation(contactInformation);
		
		customerRepository.save(customer);
		
		Assertions.assertThat(customer.getId()).isNotNull();
		Optional<Customer> optionalCustomer = customerRepository.findById(customer.getId());
		Assertions.assertThat(optionalCustomer).isPresent();
		
		Assertions.assertThat(customer.getAccounts()).isNotNull();
		Assertions.assertThat(customer.getAccounts()).hasSize(0);
		
		Customer c = optionalCustomer.get();
		Assertions.assertThat(c.getContactInformation().getPrimaryEmail()).isNotEmpty();
		Assertions.assertThat(c.getAge()).isEqualTo(21);
	}
	

	@Test
	void should_soft_delete_success_customer() {
		Customer customer = new Customer();
		customer.setAge(21);
		
		ContactInformation contactInformation = new ContactInformation();
		contactInformation.setPrimaryEmail("zeynep@salman.com");
		contactInformation.setPrimaryPhoneNumber("+905511111111");
		contactInformation.setSecondaryEmail("");
		contactInformation.setSecondaryPhoneNumber("");
		customer.setContactInformation(contactInformation);
		
		customerRepository.save(customer);
		
		Assertions.assertThat(customer.getId()).isNotNull();
		Optional<Customer> optionalCustomer = customerRepository.findById(customer.getId());
		Assertions.assertThat(optionalCustomer).isPresent();
		
		Customer c = optionalCustomer.get();
		Assertions.assertThat(c.getContactInformation().getPrimaryEmail()).isNotEmpty();
		Assertions.assertThat(c.getAge()).isEqualTo(21);
		
		customerService.delete(customer.getId(), false);
		Assertions.assertThat(customer.getId()).isNotNull();
		Assertions.assertThat(customer.isActive()).isEqualTo(false);
		Assertions.assertThat(customer.getContactInformation().getPrimaryEmail()).isEqualTo("zeynep@salman.com");
	}
	
	@Test
	void should_hard_delete_success_customer() {
		Customer customer = new Customer();
		customer.setAge(21);
		
		ContactInformation contactInformation = new ContactInformation();
		contactInformation.setPrimaryEmail("zeynep@salman.com");
		contactInformation.setPrimaryPhoneNumber("+905511111111");
		contactInformation.setSecondaryEmail("");
		contactInformation.setSecondaryPhoneNumber("");
		customer.setContactInformation(contactInformation);
		
		customerRepository.save(customer);
		
		Assertions.assertThat(customer.getId()).isNotNull();
		Optional<Customer> optionalCustomer = customerRepository.findById(customer.getId());
		Assertions.assertThat(optionalCustomer).isPresent();
		
		Customer c = optionalCustomer.get();
		Assertions.assertThat(c.getContactInformation().getPrimaryEmail()).isNotEmpty();
		Assertions.assertThat(c.getAge()).isEqualTo(21);
		
		customerService.delete(customer.getId(), true);
	}
	
}
