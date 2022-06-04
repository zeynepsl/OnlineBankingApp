package patika.bootcamp.onlinebanking.exception;

public final class UserServiceOperationException {
	public static class UserNotFound extends BaseException {

		public UserNotFound(String message) {
			super(message);
		}

	}

	private UserServiceOperationException() {
	}
}
