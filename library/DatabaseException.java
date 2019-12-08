package library;

public class DatabaseException extends Exception {

	private final String message;

	public DatabaseException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}