package patika.bootcamp.onlinebanking.dto.bank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBranchRequestDto {
	private String branchName;
	private String branchCode;
	private CreateBranchAddressRequestDto createBranchAddressRequestDto;
}
