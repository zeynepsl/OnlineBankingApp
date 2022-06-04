package patika.bootcamp.onlinebanking.service.facade.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.converter.customer.CustomerConverter;
import patika.bootcamp.onlinebanking.dto.customer.CreateCustomerRequestDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.service.CustomerService;
import patika.bootcamp.onlinebanking.service.facade.CustomerFacade;

@Service
@RequiredArgsConstructor
public class CustomerFacadeImpl implements CustomerFacade{
	private final CustomerService customerService;
	private final CustomerConverter customerConverter;
	
	@Override
	public ResponseEntity<List<CustomerResponseDto>> getAll() {
		List<Customer> customers = customerService.getAll();
		return ResponseEntity.ok(toCustomerResponseDtoList(customers));
	}

	@Override
	public ResponseEntity<CustomerResponseDto> create(CreateCustomerRequestDto createCustomerRequestDto) {
		Customer customer = customerConverter.toCustomer(createCustomerRequestDto);
		customer = customerService.create(customer);
		return new ResponseEntity<>(customerConverter.toCustomerResponseDto(customer), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CustomerResponseDto> get(Long id) throws BaseException {
		return ResponseEntity.ok(customerConverter.toCustomerResponseDto(customerService.get(id)));
	}

	@Override
	public ResponseEntity<CustomerResponseDto> update(Customer customer) {
		customer = customerService.update(customer);
		return ResponseEntity.ok(customerConverter.toCustomerResponseDto(customer));
	}

	@Override
	public ResponseEntity<CustomerResponseDto> findByEmail(String email) throws BaseException {
		return ResponseEntity.ok(customerConverter.toCustomerResponseDto(customerService.findByEmail(email)));
	}

	@Override
	public ResponseEntity<CustomerResponseDto> findByIdentityNumber(String identityNumber) throws BaseException {
		return ResponseEntity.ok(customerConverter.toCustomerResponseDto(customerService.findByIdentityNumber(identityNumber)));
	}

	@Override
	public ResponseEntity<CustomerResponseDto> findByPhoneNumber(String phoneNumber) throws BaseException {
		return ResponseEntity.ok(customerConverter.toCustomerResponseDto(customerService.findByPhoneNumber(phoneNumber)));
	}

	@Override
	public ResponseEntity<List<CustomerResponseDto>> findByAgeBetween(Integer startAge, Integer endAge) {
		List<Customer> customers = customerService.findByAgeBetween(startAge, endAge);
		return ResponseEntity.ok(toCustomerResponseDtoList(customers));
	}

	@Override
	public ResponseEntity<List<CustomerResponseDto>> finAllActiveCustomers() {
		return ResponseEntity.ok(toCustomerResponseDtoList(customerService.finAllActiveCustomers()));
	}

	@Override
	public ResponseEntity<List<CustomerResponseDto>> finAllNotActiveCustomers() {
		return ResponseEntity.ok(toCustomerResponseDtoList(customerService.finAllNotActiveCustomers()));
	}

	@Override
	public ResponseEntity<List<CustomerResponseDto>> findByIsConfirmedByAdminTrue() {
		return ResponseEntity.ok(toCustomerResponseDtoList(customerService.findByIsConfirmedByAdminTrue()));
	}

	@Override
	public ResponseEntity<List<CustomerResponseDto>> findByIsConfirmedByAdminFalse() {
		return ResponseEntity.ok(toCustomerResponseDtoList(customerService.findByIsConfirmedByAdminFalse()));
	}
	
	public List<CustomerResponseDto> toCustomerResponseDtoList(List<Customer> customers) {
		List<CustomerResponseDto> customerResponseDtoList = new ArrayList<CustomerResponseDto>();
		customers.forEach(customer -> {
			CustomerResponseDto customerResponseDto = customerConverter.toCustomerResponseDto(customer);
			customerResponseDtoList.add(customerResponseDto);
		});
		return customerResponseDtoList;
	}

	@Override
	public ResponseEntity<?> delete(Long id, Boolean hardDelete) {
		customerService.delete(id, hardDelete);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> activateCustomer(Long id) {
		customerService.activateCustomer(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> disableCustomer(Long id) {
		customerService.disableCustomer(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> confirmCustomer(Long id) {
		customerService.confirmCustomer(id);
		return ResponseEntity.ok().build();
	}
}
