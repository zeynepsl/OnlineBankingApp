package patika.bootcamp.onlinebanking.dto.card;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.dto.bank.BranchResponseDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;

@Getter
@Setter
public class CreditCardResponseDto {
	private Long id;
	private String cardNumber;
	private Boolean isActive;
	private CustomerResponseDto customerResponseDto;
	private BranchResponseDto bankBranchResponseDto;
	private BigDecimal cardLimit;
	private BigDecimal availableLimit;
	private BigDecimal periodExpenditures;
	private Date accountCutOffDate;
	private Date paymentDueDate;
	private BigDecimal amountOfDebt;
	private String cvv;
	private Date dueDate;
}
