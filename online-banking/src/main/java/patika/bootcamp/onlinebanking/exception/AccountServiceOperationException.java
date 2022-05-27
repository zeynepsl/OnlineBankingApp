package patika.bootcamp.onlinebanking.exception;

public final class AccountServiceOperationException {

	private AccountServiceOperationException() {
	}
	
	public static class AccountNotFound extends BaseException{
		public AccountNotFound(String message) {
			super(message);
		}
	}
	
	public static class AccountCanNotDeleted extends BaseException {
		public AccountCanNotDeleted(String message) {
			super(message);
		}
	}
}
