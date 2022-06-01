package patika.bootcamp.onlinebanking.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.util.converter.CurrencyConverter;

@RestController
@RequestMapping("api/currencyconverters")
@RequiredArgsConstructor
public class CuurencyConverterController {
	private final CurrencyConverter currencyConverter;
	
	@GetMapping("/{rate}/{base}")
	public ResponseEntity<?> get(@PathVariable String rate, @PathVariable String base) throws IOException{
		return ResponseEntity.ok(currencyConverter.converter(rate, base));
	}
}
