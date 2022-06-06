package patika.bootcamp.onlinebanking;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import patika.bootcamp.onlinebanking.controller.bank.BranchController;
import patika.bootcamp.onlinebanking.dto.bank.BranchResponseDto;
import patika.bootcamp.onlinebanking.dto.bank.CreateBranchAddressRequestDto;
import patika.bootcamp.onlinebanking.dto.bank.CreateBranchRequestDto;

@SpringBootTest
public class BankBranchTests {
	
	@Autowired
	BranchController bankBranchController;
	
	@Test
	void should_create_success_bankbranch() {	
		CreateBranchRequestDto branchRequestDto = new CreateBranchRequestDto();
		branchRequestDto.setBranchCode("161");
		branchRequestDto.setBranchName("Fatih Sultan Mehmet Bulvari Subesi");
		
		CreateBranchAddressRequestDto branchAddressRequestDto = new CreateBranchAddressRequestDto();
		branchAddressRequestDto.setCity("bursa");
		branchAddressRequestDto.setCountry("turkiye");
		branchAddressRequestDto.setDistrict("nilufer");
		branchAddressRequestDto.setNeighborhood("fethiye");
		
		branchRequestDto.setCreateBranchAddressRequestDto(branchAddressRequestDto);
		
		branchRequestDto.setCreateBranchAddressRequestDto(branchAddressRequestDto);
		bankBranchController.create(branchRequestDto);
		
		List<BranchResponseDto> branchResponseDtosByCity = bankBranchController.findByCity("bursa").getBody();
		branchResponseDtosByCity.forEach(branch -> {
			System.out.println(branch.getBranchName());
		});
		Assertions.assertThat(branchResponseDtosByCity.size()).isGreaterThanOrEqualTo(1);
		
		List<BranchResponseDto> branchResponseDtosByCountry = bankBranchController.findByCountry("turkiye").getBody();
		branchResponseDtosByCountry.forEach(branch -> {
			System.out.println(branch.getBranchName());
		});
		Assertions.assertThat(branchResponseDtosByCountry.size()).isGreaterThanOrEqualTo(1);
		
		List<BranchResponseDto> branchResponseDtosByDistrict = bankBranchController.findByDistrict("nilufer").getBody();
		branchResponseDtosByDistrict.forEach(branch -> {
			System.out.println(branch.getBranchName());
		});
		Assertions.assertThat(branchResponseDtosByDistrict.size()).isGreaterThanOrEqualTo(1);
		
		BranchResponseDto branchResponseDtoByNeighbor = bankBranchController.findByNeighborhood("fethiye").getBody();
		System.out.println(branchResponseDtoByNeighbor.getBranchName());
		Assertions.assertThat(branchResponseDtoByNeighbor.getBranchName()).isEqualTo("Fatih Sultan Mehmet Bulvari Subesi");
		
	}
}
