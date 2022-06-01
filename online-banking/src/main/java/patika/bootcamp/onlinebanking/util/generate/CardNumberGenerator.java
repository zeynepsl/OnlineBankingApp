package patika.bootcamp.onlinebanking.util.generate;

public final class CardNumberGenerator {
	private CardNumberGenerator() {
	}
	
	//ilk 4 hane subeKodu kalan 12 hane hesapnumarasi
	public static String generate(String bankBranchCode, String accountNumber) {
		return bankBranchCode + accountNumber;
	}
}
