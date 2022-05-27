package patika.bootcamp.onlinebanking.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.exception.BankBranchServiceOperationException;
import patika.bootcamp.onlinebanking.model.bank.BankBranch;
import patika.bootcamp.onlinebanking.repository.BankBranchRepository;
import patika.bootcamp.onlinebanking.service.BankBranchService;

@Service
@RequiredArgsConstructor
public class BankBranchServiceImpl implements BankBranchService{
	private final BankBranchRepository bankBranchRepository;

	@Override
	public BankBranch create(BankBranch bankBranch) {
		bankBranch = bankBranchRepository.save(bankBranch);
		return bankBranch;
	}

	@Override
	public BankBranch get(Long id) {
		BankBranch bankBranch = bankBranchRepository.findById(id)
				.orElseThrow(() -> new BankBranchServiceOperationException.BankBranchNotFound("bank branch not found"));
		return bankBranch;
	}

	@Override
	public BankBranch update(BankBranch bankBranch) {
		bankBranch = bankBranchRepository.save(bankBranch);
		return bankBranch;
	}

	@Override
	public void delete(Long id) {
		BankBranch bankBranch = bankBranchRepository.findById(id)
				.orElseThrow(() -> new BankBranchServiceOperationException.BankBranchNotFound("bank branch not found"));
		bankBranchRepository.delete(bankBranch);
	}
	
	@Override
	public List<BankBranch> getAll() {
		return bankBranchRepository.findAll();
	}

	@Override
	public List<BankBranch> findByCountry(String country) {
		return bankBranchRepository.findByBankBranchAddress_Country(country);
	}

	@Override
	public List<BankBranch> findByCity(String city) {
		return bankBranchRepository.findByBankBranchAddress_City(city);
	}

	@Override
	public List<BankBranch> findByDistrict(String district) {
		return bankBranchRepository.findByBankBranchAddress_District(district);
	}

	@Override
	public BankBranch findByNeighborhood(String neighborhood) {
		BankBranch bankBranch = bankBranchRepository.findByBankBranchAddress_Neighborhood(neighborhood)
				.orElseThrow(() -> new BankBranchServiceOperationException.BankBranchNotFound("bank branch not found"));
		return bankBranch;
	}
	
	@Override
	public boolean existsByNeighborhood(String neighborhood) {
		boolean result = bankBranchRepository.existsByBankBranchAddress_Neighborhood(neighborhood);
		return result;
	}
	
}
