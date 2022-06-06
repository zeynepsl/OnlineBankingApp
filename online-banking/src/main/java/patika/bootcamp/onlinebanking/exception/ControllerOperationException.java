package patika.bootcamp.onlinebanking.exception;

public final class ControllerOperationException {
	public static class IDNotValidException extends BaseException {

		public IDNotValidException(String message) {
			super(message);
		}

	}

	private ControllerOperationException() {
	}
}
