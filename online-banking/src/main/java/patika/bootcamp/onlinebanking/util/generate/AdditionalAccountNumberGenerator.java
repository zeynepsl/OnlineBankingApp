package patika.bootcamp.onlinebanking.util.generate;

import java.util.Random;

public final class AdditionalAccountNumberGenerator {
	private AdditionalAccountNumberGenerator() {
	}
	
	public static String generate() {
		Random random = new Random();
		String additionalAccountNumber = String.format("%04d", random.nextInt(9999));
		return additionalAccountNumber;
	}
}
