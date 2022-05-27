package patika.bootcamp.onlinebanking.service.facade.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.service.facade.AccountFacade;

@Service
public class AccountFacadeImpl implements AccountFacade{

	@Override
	public ResponseEntity<List<Account>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
