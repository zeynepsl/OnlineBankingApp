package patika.bootcamp.onlinebanking.service;

import java.util.List;

import patika.bootcamp.onlinebanking.core.service.BaseService;
import patika.bootcamp.onlinebanking.model.bank.Branch;

public interface BranchService extends BaseService<Branch>{
	
	List<Branch> findByCountry(String country);
	List<Branch> findByCity(String city);
	List<Branch> findByDistrict(String district);
	Branch findByNeighborhood(String neighborhood);
	boolean existsByNeighborhood(String neighborhood);
}
