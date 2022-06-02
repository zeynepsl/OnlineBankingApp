package patika.bootcamp.onlinebanking.repository.customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import patika.bootcamp.onlinebanking.model.customer.CustomerAddress;
import patika.bootcamp.onlinebanking.model.enums.AdressType;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long>{
	List<CustomerAddress> findByCustomer_Id(Long customerId);
	List<CustomerAddress> findByCustomer_IdAndAddressType(Long customerId, AdressType addressType);
}
