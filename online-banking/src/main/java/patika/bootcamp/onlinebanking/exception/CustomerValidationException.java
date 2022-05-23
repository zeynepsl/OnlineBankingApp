package patika.bootcamp.onlinebanking.exception;

public final class CustomerValidationException {
	private CustomerValidationException() {
	}
	
	public static class CustomerPhoneNumberNotValid extends BaseException{
		public CustomerPhoneNumberNotValid(String message) {
			super(message);
		}
	}
}
