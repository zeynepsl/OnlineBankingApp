package patika.bootcamp.onlinebanking.model.bank;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.base.Address;

@Entity
@Getter
@Setter
public class BranchAddress extends Address{
	
	@OneToOne(mappedBy = "bankBranchAddress")
	private Branch bankBranch;

}
