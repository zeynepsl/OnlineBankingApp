package patika.bootcamp.onlinebanking.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.base.BaseExtendedModel;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends BaseExtendedModel{
	private String password;
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)	
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<>();
	
	public void addRoles(Set<Role> roles) {
        roles.forEach(role -> role.setUsers(Set.of(this)));
        this.roles.addAll(roles);
    }

    public void removeRoles(Set<Role> roles) {
        roles.forEach(role -> role.setUsers(null));
        this.roles.removeAll(roles);
    }
	/* 
	  @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))*/
}
