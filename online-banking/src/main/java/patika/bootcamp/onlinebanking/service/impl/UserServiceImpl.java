package patika.bootcamp.onlinebanking.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.Role;
import patika.bootcamp.onlinebanking.model.User;
import patika.bootcamp.onlinebanking.repository.UserRepository;
import patika.bootcamp.onlinebanking.security.model.AuthenticationRequest;
import patika.bootcamp.onlinebanking.service.RoleService;
import patika.bootcamp.onlinebanking.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final RoleService roleService;
	@Override
	public User create(User user) throws BaseException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Role role = roleService.findByName("USER_ROLE");
		user.setRoles(Set.of(role));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		user = userRepository.save(user);
		return user;
	}

	@Override
	public User get(Long id) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
