package patika.bootcamp.onlinebanking.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.exception.CustomerAddressServiceOperationException;
import patika.bootcamp.onlinebanking.model.customer.CustomerAddress;
import patika.bootcamp.onlinebanking.model.enums.AdressType;
import patika.bootcamp.onlinebanking.repository.customer.CustomerAddressRepository;
import patika.bootcamp.onlinebanking.service.CustomerAddressService;

@Service
@RequiredArgsConstructor
public class CustomerAddressServiceImpl implements CustomerAddressService{
	private final CustomerAddressRepository customerAddressRepository;

	@Override
	public CustomerAddress create(CustomerAddress customerAddress) throws BaseException {
		CustomerAddress newcustomerAddress = customerAddressRepository.save(customerAddress);
		return newcustomerAddress;
	}

	@Override
	public CustomerAddress get(Long id) throws BaseException {
		CustomerAddress customerAddress = customerAddressRepository.findById(id)
				.orElseThrow(() -> new CustomerAddressServiceOperationException.AddressNotFound("Customer address not found"));
		return customerAddress;
	}

	@Override
	public CustomerAddress update(CustomerAddress customerAddress) {
		customerAddress = customerAddressRepository.save(customerAddress);
		return customerAddress;
	}

	@Override
	public void delete(Long id) throws BaseException {
		CustomerAddress customerAddress = get(id);
		customerAddressRepository.delete(customerAddress);
	}

	@Override
	public List<CustomerAddress> getAll() {
		return customerAddressRepository.findAll();
	}

	@Override
	public List<CustomerAddress> findByCustomer_Id(Long customerId) {
		return customerAddressRepository.findByCustomer_Id(customerId);
	}

	@Override
	public List<CustomerAddress> findByCustomer_IdAndAdressType(Long customerId, AdressType addressType) {
		return customerAddressRepository.findByCustomer_IdAndAddressType(customerId, addressType);
	}
}
