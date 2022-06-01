package patika.bootcamp.onlinebanking.repository.bank;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import patika.bootcamp.onlinebanking.model.bank.BranchAddress;

public interface BranchAddressRepository extends JpaRepository<BranchAddress, Long>{
	List<BranchAddress> findByCountry(String country);
	List<BranchAddress> findByCity(String city);
	List<BranchAddress> findByDistrict(String district);
	Optional<BranchAddress> findByNeighborhood(String neighborhood);//bir mahallede bir şube olur
	boolean existsByNeighborhood(String neighborhood);
}
