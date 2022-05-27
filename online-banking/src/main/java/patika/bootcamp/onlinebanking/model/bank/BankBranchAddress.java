package patika.bootcamp.onlinebanking.model.bank;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.base.Address;
import patika.bootcamp.onlinebanking.model.base.BaseModel;

@Entity
@Getter
@Setter
public class BankBranchAddress extends Address{
	
	@OneToOne(mappedBy = "bankBranchAddress")
	private BankBranch bankBranch;

}
