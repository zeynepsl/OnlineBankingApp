package patika.bootcamp.onlinebanking.service;

import java.util.List;

import patika.bootcamp.onlinebanking.core.service.BaseService;
import patika.bootcamp.onlinebanking.model.bank.BankBranch;

public interface BankBranchService extends BaseService<BankBranch>{
	
	List<BankBranch> findByCountry(String country);
	List<BankBranch> findByCity(String city);
	List<BankBranch> findByDistrict(String district);
	BankBranch findByNeighborhood(String neighborhood);
}
