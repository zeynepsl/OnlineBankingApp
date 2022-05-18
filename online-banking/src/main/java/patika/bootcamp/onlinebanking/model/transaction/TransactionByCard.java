package patika.bootcamp.onlinebanking.model.transaction;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TransactionByCard extends Transaction{
	private String recipientCardNo;
}
