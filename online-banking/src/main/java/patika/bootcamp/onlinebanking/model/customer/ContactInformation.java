package patika.bootcamp.onlinebanking.model.customer;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ContactInformation {
	private String primaryEmail;
	private String secondaryEmail;
	private String primaryPhoneNumber;
	private String secondaryPhoneNumber;
}
