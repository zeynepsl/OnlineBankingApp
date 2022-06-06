package patika.bootcamp.onlinebanking.controller;

import java.io.IOException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.client.RestClientException;

import patika.bootcamp.onlinebanking.exception.ApiError;
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
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<?> onIllegalStateException(IllegalStateException ex){
		return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IOException.class)
	public ResponseEntity<?> onIOException(IOException ex){
		return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Object> onNullPointerExceptionHandled(NullPointerException e) {
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "NullPointerException"));
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<Object> onInternalServerError(InternalServerError error) {
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "InternalServerError"));
	}
	
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "MissingServletRequestParameterException"));
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFound.class)
	public ResponseEntity<Object> onNotFound(NotFound error) {
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "NotFound"));
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> onConstraintViolationException(ConstraintViolationException ex){
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "ConstraintViolationException"));
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> onIllegalArgumentException(IllegalArgumentException e){
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "IllegalArgumentException"));
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RestClientException.class)
	public ResponseEntity<Object> onRestClientException(RestClientException e){
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "RestClientException"));
	}

	
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<Object>(apiError, apiError.getStatus());
	}
}
