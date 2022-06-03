package patika.bootcamp.onlinebanking.service.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.converter.customer.CustomerAddressConverter;
import patika.bootcamp.onlinebanking.dto.customer.CreateCustomerAddressRequestDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerAddressResponseDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.customer.CustomerAddress;
import patika.bootcamp.onlinebanking.model.enums.AdressType;
import patika.bootcamp.onlinebanking.service.CustomerAddressService;
import patika.bootcamp.onlinebanking.service.facade.CustomerAddressFacade;

@Service
@RequiredArgsConstructor
public class CustomerAddressFacadeImpl implements CustomerAddressFacade{
	private final CustomerAddressService customerAddressService;
	private final CustomerAddressConverter customerAddressConverter;

	@Override
	public ResponseEntity<CustomerAddressResponseDto> create(
			CreateCustomerAddressRequestDto createCustomerAddressRequestDto) throws BaseException {
		CustomerAddress customerAddress = customerAddressConverter.toCustomerAddress(createCustomerAddressRequestDto);
		CustomerAddress newcustomerAddress = customerAddressService.create(customerAddress);
		/*CustomerAddress customerAddress2 = new CustomerAddress();
		customerAddress2.setId(newcustomerAddress.getId());*/
		return new ResponseEntity<>(customerAddressConverter.toCustomerAddressResponseDto(newcustomerAddress), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CustomerAddressResponseDto> get(Long id) throws BaseException {
		CustomerAddress customerAddress = customerAddressService.get(id);
		return ResponseEntity.ok(customerAddressConverter.toCustomerAddressResponseDto(customerAddress));
	}

	@Override
	public ResponseEntity<CustomerAddressResponseDto> update(CustomerAddress customerAddress) {
		customerAddress = customerAddressService.update(customerAddress);
		return ResponseEntity.ok(customerAddressConverter.toCustomerAddressResponseDto(customerAddress));
	}

	@Override
	public ResponseEntity<?> delete(Long id) throws BaseException {
		customerAddressService.delete(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<List<CustomerAddressResponseDto>> getAll() {
		List<CustomerAddress> customerAddresses = customerAddressService.getAll();
		return ResponseEntity.ok(toCustomerAddressResponseDtoList(customerAddresses));
	}

	public List<CustomerAddressResponseDto> toCustomerAddressResponseDtoList(List<CustomerAddress> customerAddresses) {
		List<CustomerAddressResponseDto> customerAddressResponseDtos = new ArrayList<CustomerAddressResponseDto>();
		customerAddresses.forEach(address -> {
			CustomerAddressResponseDto customerAddressResponseDto = customerAddressConverter.toCustomerAddressResponseDto(address);
			customerAddressResponseDtos.add(customerAddressResponseDto);
		});
		return customerAddressResponseDtos;
	}

	@Override
	public ResponseEntity<List<CustomerAddressResponseDto>> findByCustomer_Id(Long customerId) {
		List<CustomerAddress> customerAddresses = customerAddressService.findByCustomer_Id(customerId);
		return ResponseEntity.ok(toCustomerAddressResponseDtoList(customerAddresses));
	}

	@Override
	public ResponseEntity<List<CustomerAddressResponseDto>> findByCustomer_IdAndAdressType(Long customerId,
			AdressType addressType) {
		List<CustomerAddress> customerAddresses = customerAddressService.findByCustomer_IdAndAdressType(customerId, addressType);
		return ResponseEntity.ok(toCustomerAddressResponseDtoList(customerAddresses));
	}
}
