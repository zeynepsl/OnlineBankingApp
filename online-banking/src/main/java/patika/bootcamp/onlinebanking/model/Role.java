package patika.bootcamp.onlinebanking.model;

import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.base.BaseExtendedModel;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role extends BaseExtendedModel{
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private Set<User> users = new HashSet<User>();
}
