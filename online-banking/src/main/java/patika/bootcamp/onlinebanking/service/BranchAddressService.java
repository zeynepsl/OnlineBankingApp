package patika.bootcamp.onlinebanking.service;

import java.util.List;

import patika.bootcamp.onlinebanking.core.service.BaseService;
import patika.bootcamp.onlinebanking.model.bank.BranchAddress;

public interface BranchAddressService extends BaseService<BranchAddress> {
	List<BranchAddress> findByCountry(String country);
	List<BranchAddress> findByCity(String city);
	List<BranchAddress> findByDistrict(String district);
	BranchAddress findByNeighborhood(String neighborhood);//bir mahallede bir şube olur
	boolean existsByNeighborhood(String neighborhood);
}
