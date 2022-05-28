package patika.bootcamp.onlinebanking.exception;

public final class CreditCardServiceOperationException {

	private CreditCardServiceOperationException() {
	}
	
	public static class CreditCardNotFound extends BaseException{
		public CreditCardNotFound(String message) {
			super(message);
		}
	}
}
