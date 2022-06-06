package patika.bootcamp.onlinebanking.exception;

public final class BankCardServiceOperationException {
	public static class BankCardAlreadyExists extends BaseException {

		public BankCardAlreadyExists(String message) {
			super(message);
		}

	}

	public static class InsufficientBalance extends BaseException {

		public InsufficientBalance(String message) {
			super(message);
		}

	}

	public static class PasswordWrong extends BaseException {

		public PasswordWrong(String message) {
			super(message);
		}

	}

	public static class BankCardNotFound extends BaseException {

		public BankCardNotFound(String message) {
			super(message);
		}

	}

	private BankCardServiceOperationException() {
	}
	
	
}
