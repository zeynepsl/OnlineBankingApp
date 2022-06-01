package patika.bootcamp.onlinebanking.exception;

public final class BranchAddressServiceOperation {
	public static class BranchAddressNotFound extends BaseException {

		public BranchAddressNotFound(String message) {
			super(message);
		}
		
	}

	private BranchAddressServiceOperation() {
	}
}
