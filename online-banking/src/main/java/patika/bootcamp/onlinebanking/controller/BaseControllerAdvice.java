package patika.bootcamp.onlinebanking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import patika.bootcamp.onlinebanking.exception.BaseException;

@RestControllerAdvice
public class BaseControllerAdvice {
	
	@ExceptionHandler(BaseException.class)
	public ResponseEntity<?> onBaseException(BaseException ex){
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> onMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
	}
	
	//java.lang.IllegalStateException
	
	//IOException
}
