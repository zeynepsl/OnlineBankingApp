package patika.bootcamp.onlinebanking.dto.customer;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.enums.Gender;

@Getter
@Setter
public class CustomerResponseDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String secondaryEmail;
	private String secondaryPhoneNumber;
	private String customerNumber;
	private Integer age;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthDate;
	private Gender gender;
	private boolean isActive;
	private boolean isConfirmedByAdmin;

}
