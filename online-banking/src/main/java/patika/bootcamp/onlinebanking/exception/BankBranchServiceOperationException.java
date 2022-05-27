package patika.bootcamp.onlinebanking.exception;

public final class BankBranchServiceOperationException {

	private BankBranchServiceOperationException() {
	}
	
	public static class BankBranchNotFound extends BaseException{
		public BankBranchNotFound(String message) {
			super(message);
		}
	}
	
	
}
