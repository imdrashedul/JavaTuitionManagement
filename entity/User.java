package entity;

import java.time.LocalDateTime;
import java.math.BigInteger;


public class User {
	
	private BigInteger id;
	private Email email;
	private String passHash;
	private String role;
	private LocalDateTime registered;

	public User() {}

	public User(BigInteger id, Email email, String passHash, String role, LocalDateTime registered) {
		this.id = id;
		this.email = email;
		this.passHash = passHash;
		this.role = role;
		this.registered = registered;
	}

	public void setEmail(Email email) {
		this.email = email; 
	}

	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setRegistered(LocalDateTime registered) {
		this.registered = registered;
	}

	public BigInteger getId() {
		return this.id;
	}

	public Email getEmail() {
		return this.email;
	}

	public String getPassHash() {
		return this.passHash;
	}

	public String getRole() {
		return this.role;
	}

	public LocalDateTime getRegistered() {
		return this.registered;
	}
}