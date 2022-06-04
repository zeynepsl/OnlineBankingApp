package patika.bootcamp.onlinebanking.service;

import patika.bootcamp.onlinebanking.core.service.BaseService;
import patika.bootcamp.onlinebanking.model.Role;

public interface RoleService extends BaseService<Role>{
	
	Role findByName(String name);

}
