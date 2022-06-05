package patika.bootcamp.onlinebanking.converter.customer;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.customer.CreateCustomerAddressRequestDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerAddressResponseDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.model.customer.CustomerAddress;

@Component
@RequiredArgsConstructor
public class CustomerAddressConverter {

	private final CustomerConverter customerConverter;
	public CustomerAddress toCustomerAddress(CreateCustomerAddressRequestDto createCustomerAddressRequestDto) {
		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setAddressType(createCustomerAddressRequestDto.getAddressType());
		customerAddress.setCity(createCustomerAddressRequestDto.getCity());
		customerAddress.setCountry(createCustomerAddressRequestDto.getCountry());
		
		Customer customer = new Customer();
		customer.setId(createCustomerAddressRequestDto.getCustomerId());
		customerAddress.setCustomer(customer);
		
		customerAddress.setDistrict(createCustomerAddressRequestDto.getDistrict());
		customerAddress.setNeighborhood(createCustomerAddressRequestDto.getNeighborhood());
		
		return customerAddress;
	}

	public CustomerAddressResponseDto toCustomerAddressResponseDto(CustomerAddress customerAddress) {
		CustomerAddressResponseDto customerAddressResponseDto = new CustomerAddressResponseDto();
		customerAddressResponseDto.setId(customerAddress.getId());
		customerAddressResponseDto.setAddressType(customerAddress.getAddressType());
		customerAddressResponseDto.setCity(customerAddress.getCity());
		customerAddressResponseDto.setCountry(customerAddress.getCountry());
		customerAddressResponseDto.setDistrict(customerAddress.getDistrict());
		customerAddressResponseDto.setNeighborhood(customerAddress.getNeighborhood());
		
		CustomerResponseDto customerResponseDto = customerConverter.toCustomerResponseDto(customerAddress.getCustomer());
		customerAddressResponseDto.setCustomerResponseDto(customerResponseDto);
		return customerAddressResponseDto;
	}

}
