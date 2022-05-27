package patika.bootcamp.onlinebanking.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankBranchResponseDto {
	private String branchName;
	private String branchCode;
	private BankBranchAddressResponseDto bankBranchAddressResponseDto;
}
