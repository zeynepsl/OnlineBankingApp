package patika.bootcamp.onlinebanking.exception;

public final class PrepaidCardServiceOperationException {


	private PrepaidCardServiceOperationException() {
	}
	
	public static class PrepaidCardNotFound extends BaseException{
		public PrepaidCardNotFound(String message) {
			super(message);
		}
	}
	
}
