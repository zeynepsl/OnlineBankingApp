package patika.bootcamp.onlinebanking.service.impl;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import patika.bootcamp.onlinebanking.service.CurrencyConverter;

@Service
public class CurrencyConverterImpl implements CurrencyConverter {
	private static final String URL = "https://api.apilayer.com/exchangerates_data/latest?symbols={symbols}&base={base}";
	private final String API_KEY = "pUw0APFAvkALYUYjIhLkjLgLYLqmUKyx";

	@Override
	public Double converter(String ratee, String basee) throws IOException {

		String ur = "https://api.apilayer.com/exchangerates_data/latest?symbols=" + ratee + "," + "USD" + "&base="
				+ basee + "&access_key=" + API_KEY;
		OkHttpClient client = new OkHttpClient()
				.newBuilder()
				.build();
		Request request = new Request.Builder()
				.url(ur)
				.addHeader("apikey", "pUw0APFAvkALYUYjIhLkjLgLYLqmUKyx")
				.method("GET", null)
				.build();
		Response response = client.newCall(request).execute();
		String result = response.body().string();

		return parseCurrencyFromString(result);
	}

	// bu metot anlasilmasi biraz zor olabilir ama calima mantigi basittir
	public Double parseCurrencyFromString(String result) {
		String[] sp = result.split(":");
		for (int i = 0; i < sp.length; i++) {
			System.out.println(i + ".  deger:" + sp[i]);
		}

		String base = sp[6];// bu her zaman benim aradigim currency olacak
		String pureBase;
		int endIndex = 0;
		for (int i = 0; i < base.length(); i++) {
			char c = base.charAt(i);
			if (Character.isDigit(c)) {
				endIndex = i;
			}
		}
		pureBase = (base.substring(1, endIndex + 1)).replaceAll("\\s", "");// double sayiyi alma ve bosluklardan
																			// arindirma

		Double currency = Double.valueOf(pureBase);

		return currency;
	}

}
