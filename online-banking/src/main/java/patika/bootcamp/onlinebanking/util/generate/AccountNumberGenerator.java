package patika.bootcamp.onlinebanking.util.generate;

public final class AccountNumberGenerator {
	private AccountNumberGenerator() {
	}
	
	public static String generate(String branchCode, String customerNumber, String additionalAccountNumber) {
		String accountNumber = branchCode + customerNumber + additionalAccountNumber;
		return accountNumber;
	}
}
