package patika.bootcamp.onlinebanking.util.generate;

public final class IbanGenerator {
	
	private IbanGenerator() {
	}
	
	public static String generate(String bankCode, String accountNumber) {
		String countryCode = "TR";
		String checkDigit = "70";
		String reserve = "0";
		return countryCode + checkDigit + bankCode + reserve + accountNumber;
	}
	
}
