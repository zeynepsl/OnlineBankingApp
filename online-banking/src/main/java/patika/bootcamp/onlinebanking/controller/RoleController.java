package patika.bootcamp.onlinebanking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.Role;
import patika.bootcamp.onlinebanking.service.RoleService;

@RestController
@RequestMapping("api/roles")
@RequiredArgsConstructor
public class RoleController {
	
	private final RoleService roleService;
	
	@PostMapping(path = "/")
	public ResponseEntity<Role> create(@RequestParam String name) {
		//to do: converter
		Role role = new Role();
		role.setName(name);
		role = roleService.create(role);
		return new ResponseEntity<>(role, HttpStatus.CREATED);
	}
	
	@GetMapping(path =  "/{id}")
	public ResponseEntity<Role> get(@PathVariable Long id){
		return ResponseEntity.ok(roleService.get(id));
	}
	
	@PutMapping(path = "/")
	public ResponseEntity<Role> update(@RequestBody Role role){
		return ResponseEntity.ok(roleService.update(role));
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(Long id){
		roleService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(path = "/")
	public ResponseEntity<List<Role>> getAll(){
		return ResponseEntity.ok(roleService.getAll());
	}
	
	@GetMapping(path = "/name/{name}")
	public ResponseEntity<Role> findByName(String name){
		return ResponseEntity.ok(roleService.findByName(name));
	}
}
