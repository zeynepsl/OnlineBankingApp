package patika.bootcamp.onlinebanking.exception;

public final class RoleServiceOperationException {
	public static class RoleNotFound extends BaseException {

		public RoleNotFound(String message) {
			super(message);
		}

	}

	public static class AlreadyExists extends BaseException {

		public AlreadyExists(String message) {
			super(message);
		}

	}

	private RoleServiceOperationException() {
	}
}
