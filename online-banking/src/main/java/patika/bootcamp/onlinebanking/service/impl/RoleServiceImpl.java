package patika.bootcamp.onlinebanking.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.exception.RoleServiceOperationException;
import patika.bootcamp.onlinebanking.model.Role;
import patika.bootcamp.onlinebanking.repository.RoleRepository;
import patika.bootcamp.onlinebanking.service.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
	private final RoleRepository roleRepository;

	@Override
	public Role create(Role role) throws BaseException {
		if( roleRepository.existsByName(role.getName()) ){
			throw new RoleServiceOperationException.AlreadyExists("role already exists");
		}
		role = roleRepository.save(role);
		return role;
	}

	@Override
	public Role get(Long id) throws BaseException {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new RoleServiceOperationException.RoleNotFound("role not found"));
		return role;
	}
	
	@Override
	public Role findByName(String name) {
		Role role = roleRepository.findByName(name)
				.orElseThrow(() -> new RoleServiceOperationException.RoleNotFound("role not found"));
		return role;
	}

	@Override
	public Role update(Role role) {
		role = roleRepository.save(role);
		return role;
	}

	@Override
	public void delete(Long id) throws BaseException {
		Role role = get(id);
		roleRepository.delete(role);
	}

	@Override
	public List<Role> getAll() {
		return roleRepository.findAll();
	}
}
