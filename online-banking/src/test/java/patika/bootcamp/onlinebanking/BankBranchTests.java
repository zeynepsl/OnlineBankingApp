package patika.bootcamp.onlinebanking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import patika.bootcamp.onlinebanking.controller.BankBranchController;
import patika.bootcamp.onlinebanking.model.bank.BankBranch;

@SpringBootTest
public class BankBranchTests {
	
	@Autowired
	BankBranchController bankBranchController;
	
	@Test
	void should_create_success_bankbranch() {
		BankBranch bankBranch = new BankBranch();
	}
}
