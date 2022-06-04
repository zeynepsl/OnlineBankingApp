package patika.bootcamp.onlinebanking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import patika.bootcamp.onlinebanking.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);

}
