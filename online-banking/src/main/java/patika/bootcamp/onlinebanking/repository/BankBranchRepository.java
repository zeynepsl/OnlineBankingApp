package patika.bootcamp.onlinebanking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import patika.bootcamp.onlinebanking.model.bank.BankBranch;

public interface BankBranchRepository extends JpaRepository<BankBranch, Long>{
	
	List<BankBranch> findByBankBranchAddress_Country(String country);
	List<BankBranch> findByBankBranchAddress_City(String city);
	List<BankBranch> findByBankBranchAddress_District(String district);
	Optional<BankBranch> findByBankBranchAddress_Neighborhood(String neighborhood);//bir mahallede bir şube olur
	
}
