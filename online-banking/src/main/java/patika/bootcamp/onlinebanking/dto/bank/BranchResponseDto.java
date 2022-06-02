package patika.bootcamp.onlinebanking.dto.bank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchResponseDto {
	private Long id;
	private String branchName;
	private String branchCode;
	private BranchAddressResponseDto bankBranchAddressResponseDto;
}
