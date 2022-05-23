package patika.bootcamp.onlinebanking.converter.customer;

import java.util.Date;

import org.springframework.stereotype.Component;

import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.dto.request.CreateCustomerRequestDto;
import patika.bootcamp.onlinebanking.model.customer.ContactInformation;
import patika.bootcamp.onlinebanking.model.customer.Customer;

@Component
public class CustomerConverter {

	public Customer toCustomer(CreateCustomerRequestDto createCustomerRequestDto) {
		Customer customer = new Customer();
		customer.setAge(createCustomerRequestDto.getAge());
		
		ContactInformation contactInformation = new ContactInformation();
		contactInformation.setPrimaryEmail(createCustomerRequestDto.getEmail());
		contactInformation.setPrimaryPhoneNumber(createCustomerRequestDto.getPhoneNumber());
		contactInformation.setSecondaryEmail(createCustomerRequestDto.getSecondaryEmail());
		contactInformation.setSecondaryPhoneNumber(createCustomerRequestDto.getSecondaryPhoneNumber());
		customer.setContactInformation(contactInformation);
		
		customer.setGender(createCustomerRequestDto.getGender());
		customer.setIdentityNumber(createCustomerRequestDto.getIdentityNumber());
		customer.setPassword(createCustomerRequestDto.getPassword());
		
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
		customerResponseDto.setAge(customer.getAge());
		customerResponseDto.setConfirmedByAdmin(customer.isConfirmedByAdmin());
		customerResponseDto.setGender(customer.getGender());
		
		ContactInformation contactInformation = customer.getContactInformation();
		customerResponseDto.setEmail(contactInformation.getPrimaryEmail());
		customerResponseDto.setPhoneNumber(contactInformation.getPrimaryPhoneNumber());
		customerResponseDto.setSecondaryEmail(contactInformation.getSecondaryEmail());
		customerResponseDto.setSecondaryPhoneNumber(contactInformation.getSecondaryPhoneNumber());
		
		customerResponseDto.setCreditCardResponseDto(null);
		customerResponseDto.setGoldPointAccountResponseDto(null);
		customerResponseDto.setPrepaidCardResponseDto(null);
		customerResponseDto.setPrimaryAccountResponseDtos(null);
		customerResponseDto.setSavingsAccountResponseDtos(null);
		return customerResponseDto;
	}
	
}
