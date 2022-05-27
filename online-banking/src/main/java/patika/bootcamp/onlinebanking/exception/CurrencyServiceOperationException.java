package patika.bootcamp.onlinebanking.exception;

public final class CurrencyServiceOperationException {

	private CurrencyServiceOperationException() {
	}
	
	public static class CurrencyNotFound extends BaseException{
		public CurrencyNotFound(String message) {
			super(message);
		}
	}
	
	public static class CurrencyAlreadyExists extends BaseException {
		public CurrencyAlreadyExists(String message) {
			super(message);
		}
	}
	
}
