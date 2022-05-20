package patika.bootcamp.onlinebanking;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.repository.customer.CustomerRepository;

@SpringBootTest
public class CustomerTests {
	
	@Autowired
	public CustomerRepository customerRepository;
	
	@Test
	void should_create_success_user() {
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
	}
}
