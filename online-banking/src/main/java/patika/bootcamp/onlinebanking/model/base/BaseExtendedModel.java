package patika.bootcamp.onlinebanking.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseExtendedModel extends BaseModel{
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date deletedAt;
	
	
	@Column(length = 50)
	private String createdBy;
	
	@Column(length = 50)
	private String updatedBy;
	
	@Column(length = 50)
	private String deletedBy;
}
