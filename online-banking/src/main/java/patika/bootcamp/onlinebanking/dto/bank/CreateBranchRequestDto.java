package patika.bootcamp.onlinebanking.dto.bank;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBranchRequestDto {
	@NotBlank
	private String branchName;
	
	@NotBlank
	private String branchCode;
	
	@NotNull
	private CreateBranchAddressRequestDto createBranchAddressRequestDto;
}
