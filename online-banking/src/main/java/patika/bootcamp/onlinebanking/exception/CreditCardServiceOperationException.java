package patika.bootcamp.onlinebanking.exception;

public final class CreditCardServiceOperationException {

	public static class AmountMoreThanDebt extends BaseException {

		public AmountMoreThanDebt(String message) {
			super(message);
		}

	}

	public static class CreditCardCannotDeleted extends BaseException {

		public CreditCardCannotDeleted(String message) {
			super(message);
		}

	}

	public static class WrongCardInformation extends BaseException {

		public WrongCardInformation(String message) {
			super(message);
		}

	}

	public static class InsufficientCreditCardLimit extends BaseException {

		public InsufficientCreditCardLimit(String message) {
			super(message);
		}

	}

	public static class PasswordWrong extends BaseException {

		public PasswordWrong(String message) {
			super(message);
		}

	}

	public static class InsufficientBalance extends BaseException {
		public InsufficientBalance(String message) {
			super(message);
		}
	}

	private CreditCardServiceOperationException() {
	}

	public static class CreditCardNotFound extends BaseException {
		public CreditCardNotFound(String message) {
			super(message);
		}
	}
}
