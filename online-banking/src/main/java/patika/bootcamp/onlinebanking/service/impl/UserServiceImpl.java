package patika.bootcamp.onlinebanking.service.impl;

import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.exception.UserServiceOperationException;
import patika.bootcamp.onlinebanking.model.Role;
import patika.bootcamp.onlinebanking.model.User;
import patika.bootcamp.onlinebanking.repository.UserRepository;
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
		user.getRoles().add(role);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		user = userRepository.save(user);
		return user;
	}

	@Override
	public User get(Long id) throws BaseException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserServiceOperationException.UserNotFound("user not found"));
		return user;
	}

	@Override
	public User update(User user) {
		user = userRepository.save(user);
		return user;
	}

	@Override
	public void delete(Long id) throws BaseException {
		User user = get(id);
		userRepository.delete(user);
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
}
