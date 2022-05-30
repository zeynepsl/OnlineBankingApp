package patika.bootcamp.onlinebanking.exception;

public final class TransactionServiceOperationException {

	public static class UnSupportedAccountType extends BaseException {
		public UnSupportedAccountType(String message) {
			super(message);
		}
	}

	private TransactionServiceOperationException() {
	}
	
	public static class InsufficientBalance extends BaseException {
		public InsufficientBalance(String message) {
			super(message);
		}
	}
}
