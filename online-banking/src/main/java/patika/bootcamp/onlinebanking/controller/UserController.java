package patika.bootcamp.onlinebanking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.model.User;
import patika.bootcamp.onlinebanking.security.model.AuthenticationRequest;
import patika.bootcamp.onlinebanking.service.UserService;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
//@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
public class UserController {
	private final UserService userService;
	
	@PostMapping(path = "/")
	public ResponseEntity<User> create(@RequestBody AuthenticationRequest request) {
		//to do: converter
		User user = new User();
		user.setPassword(request.getPassword());
		user.setEmail(request.getEmail());
		userService.create(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@GetMapping(path =  "/{id}")
	public ResponseEntity<User> get(@PathVariable Long id){
		return ResponseEntity.ok(userService.get(id));
	}
	
	@PutMapping(path = "/")
	public ResponseEntity<User> update(@RequestBody User user){
		return ResponseEntity.ok(userService.update(user));
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(Long id){
		userService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(path = "/")
	public ResponseEntity<List<User>> getAll(){
		return ResponseEntity.ok(userService.getAll());
	}
	
	
}
