package patika.bootcamp.onlinebanking.service.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.converter.customer.CustomerConverter;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.dto.request.CreateCustomerRequestDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.service.CustomerService;

@Service
@RequiredArgsConstructor
public class CustomerFacadeImpl implements CustomerFacade{
	private final CustomerService customerService;
	
	@Override
	public ResponseEntity<List<CustomerResponseDto>> getAll() {
		/*
		 * fetch customers from service
		 * convert customers
		 * masking
		 * */
		List<Customer> customers = customerService.getAll();
		if(customers.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		List<CustomerResponseDto> customerResponseDtos = customers
				.stream().
				map(CustomerConverter::toCustomerResponseDto).collect(Collectors.toList());
		return ResponseEntity.ok(customerResponseDtos);
	}

	@Override
	public ResponseEntity<CustomerResponseDto> create(CreateCustomerRequestDto createCustomerRequestDto) {
		Customer customer = CustomerConverter.toCustomer(createCustomerRequestDto);
		customer = customerService.create(customer);
		return new ResponseEntity<>(CustomerConverter.toCustomerResponseDto(customer), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CustomerResponseDto> get(Long id) throws BaseException {
		return ResponseEntity.ok(CustomerConverter.toCustomerResponseDto(customerService.get(id)));
	}

	@Override
	public ResponseEntity<CustomerResponseDto> update(CreateCustomerRequestDto createCustomerRequestDto) {
		Customer customer = CustomerConverter.toCustomer(createCustomerRequestDto);
		customer = customerService.update(customer);
		return ResponseEntity.ok(CustomerConverter.toCustomerResponseDto(customer));
	}

	@Override
	public ResponseEntity<CustomerResponseDto> findByEmail(String email) throws BaseException {
		return ResponseEntity.ok(CustomerConverter.toCustomerResponseDto(customerService.findByEmail(email)));
	}

	@Override
	public ResponseEntity<CustomerResponseDto> findByIdentityNumber(String identityNumber) throws BaseException {
		return ResponseEntity.ok(CustomerConverter.toCustomerResponseDto(customerService.findByIdentityNumber(identityNumber)));
	}

	@Override
	public ResponseEntity<CustomerResponseDto> findByPhoneNumber(String phoneNumber) throws BaseException {
		return ResponseEntity.ok(CustomerConverter.toCustomerResponseDto(customerService.findByPhoneNumber(phoneNumber)));
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
			CustomerResponseDto customerResponseDto = CustomerConverter.toCustomerResponseDto(customer);
			customerResponseDtoList.add(customerResponseDto);
		});
		return customerResponseDtoList;
	}
}
