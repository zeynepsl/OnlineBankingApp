package patika.bootcamp.onlinebanking.util.converter;

import java.io.IOException;
import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriTemplate;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class CurrencyConverterImpl implements CurrencyConverter {
	//private static final String URL = "https://api.apilayer.com/exchangerates_data/latest?symbols={symbols}&base={base}";
	private final String API_KEY = "pUw0APFAvkALYUYjIhLkjLgLYLqmUKyx";
	
	/*private final String apiKey = "32b956a6ca1086c0de29d387f53565cd";
	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;*/

	@Override
	public Double converter(String to, String from) throws IOException {

		//apinin endpointi symbol leri liste olarak kabul ediyordu, mecburen liste olması için USD ekledim:
		String ur = "https://api.apilayer.com/exchangerates_data/latest?symbols=" + to + "," + "USD" + "&base="
				+ from + "&access_key=" + API_KEY;
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
	
	/*public void deneme() {
		URI url = new UriTemplate(FORECAST_URL).expand(city, country, apiKey);
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        //return convertToWeatherForecast(response);
	}*/

	// bu metot anlasilmasi biraz zor olabilir ama calisma mantigi basittir :)
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
		pureBase = (base.substring(1, endIndex + 1)).replaceAll("\\s", "");// double sayiyi alma ve bosluklardan arindirma

		Double currency = Double.valueOf(pureBase);
		return currency;
	}

}
