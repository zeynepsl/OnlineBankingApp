package patika.bootcamp.onlinebanking.model.account;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.base.BaseModel;

@Entity
@Getter
@Setter
public class Currency extends BaseModel{
	private String name;
	private String code;
	private String symbol;
	
	@OneToMany(mappedBy = "currency", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Account> accounts = new HashSet<>();
	
}
