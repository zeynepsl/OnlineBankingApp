package patika.bootcamp.onlinebanking.util.generate;

import java.util.Random;

public final class CustomerNumberGenerator {
	private CustomerNumberGenerator() {
	}
	
	public static String generate() {
		Random random  = new Random();
	    int number = random.nextInt(999999);
	    String customerNumber = String.format("%06d", number);
	    return customerNumber;
	}
	
}
