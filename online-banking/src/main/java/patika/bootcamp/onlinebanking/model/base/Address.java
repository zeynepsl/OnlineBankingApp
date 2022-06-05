package patika.bootcamp.onlinebanking.model.base;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Address extends BaseModel{
	private String country;
	private String city;
	private String district;
	private String neighborhood;//mahalle
	
}
