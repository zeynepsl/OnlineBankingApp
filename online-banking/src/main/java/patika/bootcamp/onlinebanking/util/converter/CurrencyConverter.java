package patika.bootcamp.onlinebanking.util.converter;

import java.io.IOException;

public interface CurrencyConverter {
	Double converter(String to, String from) throws IOException;

	String deneme(String from, String to);
}
