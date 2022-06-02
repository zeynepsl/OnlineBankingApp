package patika.bootcamp.onlinebanking.exception;

public final class CustomerAddressServiceOperationException {
	public static class AddressNotFound extends BaseException {

		public AddressNotFound(String message) {
			super(message);
		}

	}

	private CustomerAddressServiceOperationException() {
	}
}
