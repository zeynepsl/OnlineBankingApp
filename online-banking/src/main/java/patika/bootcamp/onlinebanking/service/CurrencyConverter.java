package patika.bootcamp.onlinebanking.service;

import java.io.IOException;

public interface CurrencyConverter {
	Double converter(String rate, String base) throws IOException;
	
}
