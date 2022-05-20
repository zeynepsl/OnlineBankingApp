package patika.bootcamp.onlinebanking.exception;

public final class CustomerServiceOperationException {



	private CustomerServiceOperationException() {
	}
	
	public static class CustomerNotFound extends BaseException {
		public CustomerNotFound(String message) {
			super(message);
		}
	}
	
	public static class CustomerCanNotDeleted extends BaseException {
		public CustomerCanNotDeleted(String message) {
			super(message);
		}
	}
	
	public static class CustomerAlreadyDeleted extends BaseException {
		public CustomerAlreadyDeleted(String message) {
			super(message);
		}
	}
	
	public static class CustomerAlreadyActive extends BaseException {
		public CustomerAlreadyActive(String message) {
			super(message);
		}
	}
	
	public static class CustomerAlreadyPassive extends BaseException {
		public CustomerAlreadyPassive(String message) {
			super(message);
		}
	}
	
	public static class CustomerAlreadyConfirmedByAdmin extends BaseException {
		public CustomerAlreadyConfirmedByAdmin(String message) {
			super(message);
		}
	}
}
