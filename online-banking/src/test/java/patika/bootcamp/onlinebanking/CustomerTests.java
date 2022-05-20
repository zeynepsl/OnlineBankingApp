package patika.bootcamp.onlinebanking;

import java.lang.reflect.Method;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.repository.customer.CustomerRepository;
import patika.bootcamp.onlinebanking.service.CustomerService;
import patika.bootcamp.onlinebanking.service.impl.CustomerServiceImpl;

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
		customer.setEmail("zeynep@salmansalman.com");
		customer.setPhoneNumber("+905511111111");
		customerRepository.save(customer);
		
		Assertions.assertThat(customer.getId()).isNotNull();
		Optional<Customer> optionalCustomer = customerRepository.findById(customer.getId());
		Assertions.assertThat(optionalCustomer).isPresent();
		
		Assertions.assertThat(customer.getPrimaryAccounts()).isNotNull();
		Assertions.assertThat(customer.getSavingsAccounts()).isNotNull();
		Assertions.assertThat(customer.getPrimaryAccounts()).hasSize(0);
		Assertions.assertThat(customer.getSavingsAccounts()).hasSize(0);
		
		Customer c = optionalCustomer.get();
		Assertions.assertThat(c.getEmail()).isNotEmpty();
		Assertions.assertThat(c.getAge()).isEqualTo(21);
	}
	

	@Test
	void should_soft_delete_success_customer() {
		Customer customer = new Customer();
		customer.setAge(21);
		customer.setEmail("zeynep@salman.com");
		customer.setPhoneNumber("+905511111111");
		customerRepository.save(customer);
		
		Assertions.assertThat(customer.getId()).isNotNull();
		Optional<Customer> optionalCustomer = customerRepository.findById(customer.getId());
		Assertions.assertThat(optionalCustomer).isPresent();
		
		Customer c = optionalCustomer.get();
		Assertions.assertThat(c.getEmail()).isNotEmpty();
		Assertions.assertThat(c.getAge()).isEqualTo(21);
		
		customerService.delete(customer.getId(), false);
		Assertions.assertThat(customer.getId()).isNotNull();
		Assertions.assertThat(customer.isActive()).isEqualTo(false);
		Assertions.assertThat(customer.getEmail()).isEqualTo("zeynep@salman.com");
	}
	
	@Test
	void should_hard_delete_success_customer() {
		Customer customer = new Customer();
		customer.setAge(21);
		customer.setEmail("zeynep@salman.com");
		customer.setPhoneNumber("+905511111111");
		customerRepository.save(customer);
		
		Assertions.assertThat(customer.getId()).isNotNull();
		Optional<Customer> optionalCustomer = customerRepository.findById(customer.getId());
		Assertions.assertThat(optionalCustomer).isPresent();
		
		Customer c = optionalCustomer.get();
		Assertions.assertThat(c.getEmail()).isNotEmpty();
		Assertions.assertThat(c.getAge()).isEqualTo(21);
		
		customerService.delete(customer.getId(), true);
	}
	
}
