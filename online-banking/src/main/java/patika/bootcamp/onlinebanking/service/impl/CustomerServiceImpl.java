package patika.bootcamp.onlinebanking.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import patika.bootcamp.onlinebanking.converter.customer.CustomerConverter;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.dto.request.CreateCustomerRequestDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.exception.CustomerServiceOperationException;
import patika.bootcamp.onlinebanking.model.account.PrimaryAccount;
import patika.bootcamp.onlinebanking.model.account.SavingsAccount;
import patika.bootcamp.onlinebanking.model.card.CreditCard;
import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.repository.customer.CustomerRepository;
import patika.bootcamp.onlinebanking.service.CustomerService;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerConverter customerConverter;

	@Override
	public CustomerResponseDto create(CreateCustomerRequestDto createCustomerRequestDto) {
		Customer customer = customerConverter.toCustomer(createCustomerRequestDto);
		customerRepository.save(customer);
		return customerConverter.toCustomerResponseDto(customer);
	}

	@Override
	public CustomerResponseDto get(Long id) throws BaseException {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerServiceOperationException.CustomerNotFound("Customer not found"));
		return customerConverter.toCustomerResponseDto(customer);
	}

	@Override
	public void delete(Long id, Boolean hardDelete) throws BaseException {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerServiceOperationException.CustomerNotFound("Customer not found"));
		if (doHaveMoneyInAnyAccount(customer)) {
			throw new CustomerServiceOperationException.CustomerCanNotDeleted(
					"Customer not deleted because Customer has money in the primary account or savvings account");
		}
		if (isThereDebtAmountOnCreditCard(customer)) {
			throw new CustomerServiceOperationException.CustomerCanNotDeleted(
					"Customer not deleted because Customer has debt in the credit card");
		}
		if (!customer.isActive()) {
			throw new CustomerServiceOperationException.CustomerAlreadyDeleted("this customer already deleted");
		}
		if (hardDelete) {
			customerRepository.delete(customer);
			log.info("customer deleted from db");
			return;
		}

		customer.setActive(false);
		customer.setDeletedAt(new Date());
		customer.setDeletedBy("Zeynep Salman");
		log.info("id: {}, customer deleted", customer.getId());
		customerRepository.save(customer);
	}

	public boolean isThereDebtAmountOnCreditCard(Customer customer) {
		CreditCard creditCard = customer.getCreditCard();
		if(creditCard == null) {
			return false;
		}
		BigDecimal amountOfDebt = creditCard.getAmountOfDebt();
		if (amountOfDebt != null) {
			if (amountOfDebt.compareTo(BigDecimal.valueOf(0)) > 0) {
				return true;
			}
		}

		return false;
	}

	public boolean doHaveMoneyInAnyAccount(Customer customer) {
		Set<PrimaryAccount> primaryAccounts = customer.getPrimaryAccounts();	
		Set<SavingsAccount> savingsAccounts = customer.getSavingsAccounts();

		if (primaryAccounts.isEmpty() && savingsAccounts.isEmpty()) {
			return false;
		}

		for (PrimaryAccount primaryAccount : customer.getPrimaryAccounts()) {
			if (primaryAccount.getAccountBalance().compareTo(BigDecimal.valueOf(0)) > 0) {
				return true;
			}
		}
		for (SavingsAccount savingsAccount : customer.getSavingsAccounts()) {
			if (savingsAccount.getAccountBalance().compareTo(BigDecimal.valueOf(0)) > 0) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void update(CreateCustomerRequestDto createCustomerRequestDto) {
		Customer customer = customerConverter.toCustomer(createCustomerRequestDto);
		customerRepository.save(customer);
	}

	@Override
	public List<CustomerResponseDto> getAll() {
		return toCustomerResponseDtoList(customerRepository.findAll());
	}

	@Override
	public CustomerResponseDto findByEmail(String email) throws BaseException {
		Customer customer = customerRepository.findByEmail(email)
				.orElseThrow(() -> new CustomerServiceOperationException.CustomerNotFound("Customer not found"));
		return customerConverter.toCustomerResponseDto(customer);
	}

	@Override
	public CustomerResponseDto findByIdentityNumber(String identityNumber) throws BaseException {
		Customer customer = customerRepository.findByIdentityNumber(identityNumber)
				.orElseThrow(() -> new CustomerServiceOperationException.CustomerNotFound("customer not found"));
		return customerConverter.toCustomerResponseDto(customer);
	}

	@Override
	public CustomerResponseDto findByPhoneNumber(String phoneNumber) throws BaseException {
		Customer customer = customerRepository.findByIdentityNumber(phoneNumber)
				.orElseThrow(() -> new CustomerServiceOperationException.CustomerNotFound("customer not found"));
		return customerConverter.toCustomerResponseDto(customer);
	}

	@Override
	public List<CustomerResponseDto> findByAgeBetween(Integer startAge, Integer endAge) {
		return toCustomerResponseDtoList(customerRepository.findByAgeBetween(startAge, endAge));
	}

	@Override
	public List<CustomerResponseDto> finAllActiveCustomers() {
		return toCustomerResponseDtoList(customerRepository.findByIsActiveTrue());
	}

	@Override
	public List<CustomerResponseDto> finAllNotActiveCustomers() {
		return toCustomerResponseDtoList(customerRepository.findByIsActiveFalse());
	}

	@Override
	public List<CustomerResponseDto> findByIsConfirmedByAdminTrue() {
		return toCustomerResponseDtoList(customerRepository.findByIsConfirmedByAdminTrue());
	}

	@Override
	public List<CustomerResponseDto> findByIsConfirmedByAdminFalse() {
		return toCustomerResponseDtoList(customerRepository.findByIsConfirmedByAdminFalse());
	}

	private List<CustomerResponseDto> toCustomerResponseDtoList(List<Customer> customers) {
		List<CustomerResponseDto> customerResponseDtoList = new ArrayList<CustomerResponseDto>();
		customers.forEach(customer -> {
			CustomerResponseDto customerResponseDto = customerConverter.toCustomerResponseDto(customer);
			customerResponseDtoList.add(customerResponseDto);
		});
		return customerResponseDtoList;
	}

	@Override
	public void activateCustomer(Long id) throws BaseException {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerServiceOperationException.CustomerNotFound("Customer not found"));
		if (customer.isActive()) {
			throw new CustomerServiceOperationException.CustomerAlreadyActive("this customer already active");
		}
		log.info("customer will be active, isActive: {}", customer.isActive());
		customer.setActive(true);
		customerRepository.save(customer);
		log.info("customer activated, isActive: {}", customer.isActive());
	}

	@Override
	public void disableCustomer(Long id) throws BaseException {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerServiceOperationException.CustomerNotFound("Customer not found"));
		if (!customer.isActive()) {
			throw new CustomerServiceOperationException.CustomerAlreadyPassive("the client is already disabled");
		}
		log.info("customer will be disable, isActive: {}", customer.isActive());
		customer.setActive(false);
		customerRepository.save(customer);
		log.info("customer disabled, isActive: {}", customer.isActive());
	}

	@Override
	public void confirmCustomer(Long id) throws BaseException {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerServiceOperationException.CustomerNotFound("Customer not found"));
		if (customer.isConfirmedByAdmin()) {
			throw new CustomerServiceOperationException.CustomerAlreadyConfirmedByAdmin(
					"customer is already confirmed by admin");
		}
		log.info("customer will confirm by admin, isConfirmedByAdmin: {}", customer.isConfirmedByAdmin());
		customer.setConfirmedByAdmin(true);
		customerRepository.save(customer);
		log.info("customer confirmed by admin, isConfirmedByAdmin: {}", customer.isConfirmedByAdmin());
	}

}
