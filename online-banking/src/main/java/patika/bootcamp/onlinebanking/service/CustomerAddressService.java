package patika.bootcamp.onlinebanking.service;

import java.util.List;

import patika.bootcamp.onlinebanking.core.service.BaseService;
import patika.bootcamp.onlinebanking.model.customer.CustomerAddress;
import patika.bootcamp.onlinebanking.model.enums.AdressType;

public interface CustomerAddressService extends BaseService<CustomerAddress>{
	List<CustomerAddress> findByCustomer_Id(Long customerId);
	List<CustomerAddress> findByCustomer_IdAndAdressType(Long customerId,AdressType addressType);
}
