package entity;

public class Email {
	private String email;

	public Email() {}

	public Email(String email) {
		this.email = email;
	}

	public String get() {
		return this.email;
	}

	public void set(String email) {
		this.email = email;
	}
}