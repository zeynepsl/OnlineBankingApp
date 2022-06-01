package patika.bootcamp.onlinebanking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import patika.bootcamp.onlinebanking.controller.BranchController;
import patika.bootcamp.onlinebanking.model.bank.Branch;

@SpringBootTest
public class BankBranchTests {
	
	@Autowired
	BranchController bankBranchController;
	
	@Test
	void should_create_success_bankbranch() {
		Branch bankBranch = new Branch();
	}
}
