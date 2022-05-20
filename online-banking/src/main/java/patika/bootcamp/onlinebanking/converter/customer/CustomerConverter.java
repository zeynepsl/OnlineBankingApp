package patika.bootcamp.onlinebanking.converter.customer;

import java.util.Date;

import org.springframework.stereotype.Component;

import patika.bootcamp.onlinebanking.dto.customer.CreateCustomerRequestDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.model.customer.Customer;

@Component
public class CustomerConverter {

	public Customer toCustomer(CreateCustomerRequestDto createCustomerRequestDto) {
		Customer customer = new Customer();
		customer.setAge(createCustomerRequestDto.getAge());		
		customer.setEmail(createCustomerRequestDto.getEmail());
		customer.setGender(createCustomerRequestDto.getGender());
		customer.setIdentityNumber(createCustomerRequestDto.getIdentityNumber());
		customer.setPassword(createCustomerRequestDto.getPassword());
		customer.setPhoneNumber(createCustomerRequestDto.getPhoneNumber());
		
		customer.setCreatedAt(new Date());
		customer.setCreatedBy("Zeynep Salman");
		customer.setUpdatedAt(new Date());
		customer.setUpdatedBy("Zeynep Salman");
		
		return customer;
	}

	public CustomerResponseDto toCustomerResponseDto(Customer customer) {
		CustomerResponseDto customerResponseDto = new CustomerResponseDto();
		customerResponseDto.setActive(customer.isActive());
		customerResponseDto.setAge(customer.getAge());
		customerResponseDto.setConfirmedByAdmin(customer.isConfirmedByAdmin());
		customerResponseDto.setEmail(customer.getEmail());
		customerResponseDto.setGender(customer.getGender());
		customerResponseDto.setIdentityNumber(customer.getIdentityNumber());
		customerResponseDto.setPassword(customer.getPassword());
		customerResponseDto.setPhoneNumber(customer.getPhoneNumber());
	
		customerResponseDto.setCreditCardResponseDto(null);
		customerResponseDto.setGoldPointAccountResponseDto(null);
		customerResponseDto.setPrepaidCardResponseDto(null);
		customerResponseDto.setPrimaryAccountResponseDtos(null);
		customerResponseDto.setSavingsAccountResponseDtos(null);
		return customerResponseDto;
	}
	
}
