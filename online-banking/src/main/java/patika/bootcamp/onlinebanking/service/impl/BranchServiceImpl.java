package patika.bootcamp.onlinebanking.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.exception.BranchServiceOperationException;
import patika.bootcamp.onlinebanking.model.bank.Branch;
import patika.bootcamp.onlinebanking.repository.bank.BranchRepository;
import patika.bootcamp.onlinebanking.service.BranchService;

@Service
@RequiredArgsConstructor
@Transactional
public class BranchServiceImpl implements BranchService{
	private final BranchRepository bankBranchRepository;

	@Override
	public Branch create(Branch bankBranch) {
		//bir mahallede 1 sube olabilir:
		if(bankBranch.getBankBranchAddress().getNeighborhood() != null) {
			boolean isExistBranch = existsByNeighborhood(bankBranch.getBankBranchAddress().getNeighborhood());
			if(isExistBranch) {
				throw new BranchServiceOperationException.BankBranchAlreadyExists("already exists");
			}
		}
		
		bankBranch.getBankBranchAddress().getNeighborhood();
		bankBranch = bankBranchRepository.save(bankBranch);
		return bankBranch;
	}

	@Override
	public Branch get(Long id) {
		Branch bankBranch = bankBranchRepository.findById(id)
				.orElseThrow(() -> new BranchServiceOperationException.BankBranchNotFound("bank branch not found"));
		return bankBranch;
	}

	@Override
	public Branch update(Branch bankBranch) {
		bankBranch = bankBranchRepository.save(bankBranch);
		return bankBranch;
	}

	@Override
	public void delete(Long id) {
		Branch bankBranch = bankBranchRepository.findById(id)
				.orElseThrow(() -> new BranchServiceOperationException.BankBranchNotFound("bank branch not found"));
		bankBranchRepository.delete(bankBranch);
	}
	
	@Override
	public List<Branch> getAll() {
		return bankBranchRepository.findAll();
	}

	@Override
	public List<Branch> findByCountry(String country) {
		return bankBranchRepository.findByBankBranchAddress_Country(country);
	}

	@Override
	public List<Branch> findByCity(String city) {
		return bankBranchRepository.findByBankBranchAddress_City(city);
	}

	@Override
	public List<Branch> findByDistrict(String district) {
		return bankBranchRepository.findByBankBranchAddress_District(district);
	}

	@Override
	public Branch findByNeighborhood(String neighborhood) {
		Branch bankBranch = bankBranchRepository.findByBankBranchAddress_Neighborhood(neighborhood)
				.orElseThrow(() -> new BranchServiceOperationException.BankBranchNotFound("bank branch not found"));
		return bankBranch;
	}
	
	@Override
	public boolean existsByNeighborhood(String neighborhood) {
		boolean result = bankBranchRepository.existsByBankBranchAddress_Neighborhood(neighborhood);
		return result;
	}
	
}
