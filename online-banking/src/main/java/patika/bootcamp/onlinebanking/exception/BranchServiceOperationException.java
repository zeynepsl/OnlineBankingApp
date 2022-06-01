package patika.bootcamp.onlinebanking.exception;

public final class BranchServiceOperationException {

	private BranchServiceOperationException() {
	}
	
	public static class BankBranchNotFound extends BaseException{
		public BankBranchNotFound(String message) {
			super(message);
		}
	}
	
	public static class BankBranchAlreadyExists extends BaseException {
		public BankBranchAlreadyExists(String message) {
			super(message);
		}
	}
	
}
