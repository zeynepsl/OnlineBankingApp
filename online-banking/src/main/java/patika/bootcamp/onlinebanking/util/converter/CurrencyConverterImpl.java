package patika.bootcamp.onlinebanking.util.converter;

import java.io.IOException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
@RequiredArgsConstructor
public class CurrencyConverterImpl implements CurrencyConverter {

	private final String API_KEY = "pUw0APFAvkALYUYjIhLkjLgLYLqmUKyx";

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
		
		System.out.println(result);

		return parseCurrencyFromString(result);
	}
	
	/*
	 gelen yanit:
	 
	 {
    "success": true,
    "timestamp": 1654255204,
    "base": "USD",
    "date": "2022-06-03",
    "rates": {
        "TRY": 16.511475,
        "USD": 1
         }
    }
	 */

	// bu metot gelen responsun icindeki hedef para birimini almayi amaclar:
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
	
	/*

0.  deger:{
    "success"
1.  deger: true,
    "timestamp"
2.  deger: 1654255204,
    "base"
3.  deger: "USD",
    "date"
4.  deger: "2022-06-03",
    "rates"
5.  deger: {
        "TRY"
6.  deger: 16.511475,
        "USD"
7.  deger: 1
    }
}

*/

}
