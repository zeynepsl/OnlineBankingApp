package patika.bootcamp.onlinebanking.util;

import java.util.Random;

public final class AdditionalAccountNumberGenerator {
	private AdditionalAccountNumberGenerator() {
	}
	
	public static String generate() {
		Random random = new Random();
		String additionalAccountNumber = String.format("%04d%n", random.nextInt(10000));
		return additionalAccountNumber;
	}
}
