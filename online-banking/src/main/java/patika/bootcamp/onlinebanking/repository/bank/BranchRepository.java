package patika.bootcamp.onlinebanking.repository.bank;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import patika.bootcamp.onlinebanking.model.bank.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long>{
	
	List<Branch> findByBankBranchAddress_Country(String country);
	List<Branch> findByBankBranchAddress_City(String city);
	List<Branch> findByBankBranchAddress_District(String district);
	Optional<Branch> findByBankBranchAddress_Neighborhood(String neighborhood);//bir mahallede bir şube olur
	boolean existsByBankBranchAddress_Neighborhood(String neighborhood);
}
