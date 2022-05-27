package patika.bootcamp.onlinebanking.service.facade;

import java.util.List;

import org.springframework.http.ResponseEntity;

import patika.bootcamp.onlinebanking.model.account.Account;

public interface AccountFacade {
	ResponseEntity<List<Account>> getAll(); 
}
