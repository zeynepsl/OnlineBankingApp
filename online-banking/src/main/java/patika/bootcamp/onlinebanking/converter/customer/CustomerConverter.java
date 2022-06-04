package patika.bootcamp.onlinebanking.converter.customer;

import java.util.Date;

import org.springframework.stereotype.Component;

import patika.bootcamp.onlinebanking.dto.customer.CreateCustomerRequestDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.model.User;
import patika.bootcamp.onlinebanking.model.customer.ContactInformation;
import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.util.generate.CustomerNumberGenerator;

@Component
public class CustomerConverter {

	public Customer toCustomer(CreateCustomerRequestDto createCustomerRequestDto) {
		Customer customer = new Customer();
		customer.setAge(createCustomerRequestDto.getAge());
		customer.setFirstName(createCustomerRequestDto.getFirstName());
		customer.setLastName(createCustomerRequestDto.getLastName());
		customer.setBirthDate(createCustomerRequestDto.getBirthDate());
		customer.setCustomerNumber(CustomerNumberGenerator.generate());
		
		User user = new User();
		user.setPassword(createCustomerRequestDto.getPassword());
		user.setEmail(createCustomerRequestDto.getEmail());
		customer.setUser(user);
		user.setCustomer(customer);
		
		ContactInformation contactInformation = new ContactInformation();
		contactInformation.setPrimaryEmail(createCustomerRequestDto.getEmail());
		contactInformation.setPrimaryPhoneNumber(createCustomerRequestDto.getPhoneNumber());
		contactInformation.setSecondaryEmail(createCustomerRequestDto.getSecondaryEmail());
		contactInformation.setSecondaryPhoneNumber(createCustomerRequestDto.getSecondaryPhoneNumber());
		customer.setContactInformation(contactInformation);
		
		customer.setGender(createCustomerRequestDto.getGender());
		customer.setIdentityNumber(createCustomerRequestDto.getIdentityNumber());
		
		customer.setCreatedAt(new Date());
		customer.setCreatedBy("Zeynep Salman");
		customer.setUpdatedAt(new Date());
		customer.setUpdatedBy("Zeynep Salman");
		
		return customer;
	}

	public CustomerResponseDto toCustomerResponseDto(Customer customer) {
		CustomerResponseDto customerResponseDto = new CustomerResponseDto();
		customerResponseDto.setId(customer.getId());
		customerResponseDto.setActive(customer.isActive());
		customerResponseDto.setFirstName(customer.getFirstName());
		customerResponseDto.setLastName(customer.getLastName());
		customerResponseDto.setBirthDate(customer.getBirthDate());
		customerResponseDto.setAge(customer.getAge());
		customerResponseDto.setConfirmedByAdmin(customer.isConfirmedByAdmin());
		customerResponseDto.setGender(customer.getGender());
		
		ContactInformation contactInformation = customer.getContactInformation();
		customerResponseDto.setEmail(customer.getContactInformation().getPrimaryEmail());
		customerResponseDto.setPhoneNumber(contactInformation.getPrimaryPhoneNumber());
		customerResponseDto.setSecondaryEmail(contactInformation.getSecondaryEmail());
		customerResponseDto.setSecondaryPhoneNumber(contactInformation.getSecondaryPhoneNumber());
		
		customerResponseDto.setCustomerNumber(customer.getCustomerNumber());
		
		return customerResponseDto;
	}
	
}
