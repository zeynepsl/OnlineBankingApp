package patika.bootcamp.onlinebanking.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBankBranchRequestDto {
	private String branchName;
	private String branchCode;
	private Long bankBranchAddressId;
}
