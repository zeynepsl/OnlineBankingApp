package patika.bootcamp.onlinebanking.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.exception.BranchAddressServiceOperation;
import patika.bootcamp.onlinebanking.model.bank.BranchAddress;
import patika.bootcamp.onlinebanking.repository.bank.BranchAddressRepository;
import patika.bootcamp.onlinebanking.service.BranchAddressService;

@Service
@RequiredArgsConstructor
@Transactional
public class BranchAddressServiceImpl implements BranchAddressService{

	private final BranchAddressRepository branchAddressRepository; 
	@Override
	public BranchAddress create(BranchAddress branchAddress) throws BaseException {
		branchAddress.getNeighborhood();
		
		branchAddress = branchAddressRepository.save(branchAddress);
		return branchAddress;
	}

	@Override
	public BranchAddress get(Long id) throws BaseException {
		BranchAddress branchAddress = branchAddressRepository.findById(id)
				.orElseThrow(() -> new BranchAddressServiceOperation.BranchAddressNotFound("Branch address not found"));
		return branchAddress;
	}

	@Override
	public BranchAddress update(BranchAddress branchAddress) {
		branchAddress = branchAddressRepository.save(branchAddress);
		return branchAddress;
	}

	@Override
	public void delete(Long id) throws BaseException {
		BranchAddress branchAddress = get(id);
		branchAddressRepository.delete(branchAddress);
	}

	@Override
	public List<BranchAddress> getAll() {
		return branchAddressRepository.findAll();
	}

	@Override
	public List<BranchAddress> findByCountry(String country) {
		return branchAddressRepository.findByCountry(country);
	}

	@Override
	public List<BranchAddress> findByCity(String city) {
		return branchAddressRepository.findByCity(city);
	}

	@Override
	public List<BranchAddress> findByDistrict(String district) {
		return branchAddressRepository.findByDistrict(district);
	}

	@Override
	public BranchAddress findByNeighborhood(String neighborhood) {
		BranchAddress branchAddress = branchAddressRepository.findByNeighborhood(neighborhood)
				.orElseThrow(() -> new BranchAddressServiceOperation.BranchAddressNotFound("address not found"));
		return branchAddress;
	}

	@Override
	public boolean existsByNeighborhood(String neighborhood) {
		boolean result = branchAddressRepository.existsByNeighborhood(neighborhood);
		return result;
	}

}
