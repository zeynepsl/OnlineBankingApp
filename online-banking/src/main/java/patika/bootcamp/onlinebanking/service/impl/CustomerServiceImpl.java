package patika.bootcamp.onlinebanking.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.exception.CustomerServiceOperationException;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.bank.Branch;
import patika.bootcamp.onlinebanking.model.card.CreditCard;
import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.model.customer.CustomerAddress;
import patika.bootcamp.onlinebanking.model.enums.AccountType;
import patika.bootcamp.onlinebanking.repository.customer.CustomerRepository;
import patika.bootcamp.onlinebanking.service.BranchService;
import patika.bootcamp.onlinebanking.service.CurrencyService;
import patika.bootcamp.onlinebanking.service.CustomerService;
import patika.bootcamp.onlinebanking.util.AccountNumberGenerator;
import patika.bootcamp.onlinebanking.util.AdditionalAccountNumberGenerator;
import patika.bootcamp.onlinebanking.util.CustomerNumberGenerator;
import patika.bootcamp.onlinebanking.util.IbanGenerator;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final BranchService bankBranchService;
	private final CurrencyService currencyService;

	@Override
	public Customer create(Customer customer) {
		customerRepository.save(customer);
		
		Account account = createCheckingAccountWhileCustomerIsCreated(customer);
		customer.setAccounts(Set.of(account));
		customerRepository.save(customer);
		return customer;
	}

	public Account createCheckingAccountWhileCustomerIsCreated(Customer customer) {
		Account account = new Account();
		account.setAccountType(AccountType.CHECKING_ACCOUNT);
		account.setBankCode("207");
		String customerNumber = CustomerNumberGenerator.generate();
		String additionalAccountNumber = AdditionalAccountNumberGenerator.generate();

		account.setAdditionalAccountNumber(additionalAccountNumber);
		account.setCreatedAt(new Date());
		account.setCreatedBy("Zeynep Salman");

		account.setCurrency(currencyService.findByCode("TRY"));
		account.setIban(IbanGenerator.generate("207", account.getAccountNumber()));

		Set<CustomerAddress> customerAddresses = customer.getCustomerAddresses();
		if (customerAddresses.isEmpty()) {
			return account;
		}

		customerAddresses.forEach(customerAddres -> {
			String country = customerAddres.getCountry();
			String city = customerAddres.getCity();
			String district = customerAddres.getDistrict();
			String neighborhood = customerAddres.getNeighborhood();

			List<Branch> bankBranchsByDistrict = bankBranchService.findByDistrict(district);
			List<Branch> bankBranchsByCity = bankBranchService.findByCity(city);
			List<Branch> bankBranchByCountry = bankBranchService.findByCountry(country);
			Branch bankBranchFromNeighborhood = bankBranchService.findByNeighborhood(neighborhood);

			if (bankBranchByCountry != null && bankBranchsByCity != null && bankBranchsByDistrict != null
					&& bankBranchFromNeighborhood != null) {
				account.setBankBranch(bankBranchFromNeighborhood);
			} 
			else if (bankBranchByCountry != null && bankBranchsByCity != null && bankBranchsByDistrict != null) {
				account.setBankBranch(bankBranchsByDistrict.get(0));
			} 
			else if (bankBranchByCountry != null && bankBranchsByCity != null) {
				account.setBankBranch(bankBranchsByCity.get(0));
			} 
			else {
				account.setBankBranch(bankBranchByCountry.get(0));
			}
		});
		String branchCode = account.getBankBranch().getBranchCode();
		String accountNumber = AccountNumberGenerator.generate(branchCode, customerNumber, additionalAccountNumber);
		account.setAccountNumber(accountNumber);
		return account;
	}

	@Override
	public Customer get(Long id) throws BaseException {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerServiceOperationException.CustomerNotFound("Customer not found"));
		return customer;
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
			delete(id);
			log.info("customer deleted from db");
			return;
		}

		customer.setActive(false);
		customer.setDeletedAt(new Date());
		customer.setDeletedBy("Zeynep Salman");
		log.info("id: {}, customer deleted", customer.getId());
		customerRepository.save(customer);
	}

	@Override
	public void delete(Long id) throws BaseException {
		customerRepository.deleteById(id);
	}

	public boolean isThereDebtAmountOnCreditCard(Customer customer) {
		CreditCard creditCard = customer.getCreditCard();
		if (creditCard == null) {
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
		Set<Account> accounts = customer.getAccounts();

		if (accounts.isEmpty()) {
			return false;
		}

		for (Account account : customer.getAccounts()) {
			if (account.getAccountBalance().compareTo(BigDecimal.valueOf(0)) > 0) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Customer update(Customer customer) {
		customerRepository.save(customer);
		return customer;
	}

	@Override
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer findByEmail(String email) throws BaseException {
		Customer customer = customerRepository.findByContactInformation_PrimaryEmail(email)
				.orElseThrow(() -> new CustomerServiceOperationException.CustomerNotFound("Customer not found"));
		return customer;
	}

	@Override
	public Customer findByIdentityNumber(String identityNumber) throws BaseException {
		Customer customer = customerRepository.findByIdentityNumber(identityNumber)
				.orElseThrow(() -> new CustomerServiceOperationException.CustomerNotFound("customer not found"));
		return customer;
	}

	@Override
	public Customer findByPhoneNumber(String phoneNumber) throws BaseException {
		Customer customer = customerRepository.findByContactInformation_PrimaryPhoneNumber(phoneNumber)
				.orElseThrow(() -> new CustomerServiceOperationException.CustomerNotFound("customer not found"));
		return customer;
	}

	@Override
	public List<Customer> findByAgeBetween(Integer startAge, Integer endAge) {
		return customerRepository.findByAgeBetween(startAge, endAge);
	}

	@Override
	public List<Customer> finAllActiveCustomers() {
		return customerRepository.findByIsActiveTrue();
	}

	@Override
	public List<Customer> finAllNotActiveCustomers() {
		return customerRepository.findByIsActiveFalse();
	}

	@Override
	public List<Customer> findByIsConfirmedByAdminTrue() {
		return customerRepository.findByIsConfirmedByAdminTrue();
	}

	@Override
	public List<Customer> findByIsConfirmedByAdminFalse() {
		return customerRepository.findByIsConfirmedByAdminFalse();
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
