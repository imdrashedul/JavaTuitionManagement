package entity;

import java.time.LocalDateTime;
import java.math.BigInteger;


public class SessionMeta {
	
	private BigInteger roll;
	private int sessionId;
	private int sectionId;
	private BigInteger userId;
	private String status;
	private LocalDateTime migrated;

	public SessionMeta() {}

	public SessionMeta(BigInteger roll, int sessionId, int sectionId, BigInteger userId, String status, LocalDateTime migrated) {
		this.roll = roll;
		this.sessionId = sessionId;
		this.sectionId = sectionId;
		this.userId = userId;
		this.status = status;
		this.migrated = migrated;
	}

	public void setUser(BigInteger userId) {
		this.userId = userId;
	}

	public void setSession(int sessionId) {
		this.sessionId = sessionId; 
	}

	public void setSection(int sectionId) {
		this.sectionId = sessionId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMigration(LocalDateTime migrated) {
		this.migrated = migrated;
	}

	public BigInteger getRoll() {
		return this.roll;
	}

	public BigInteger getUser() {
		return this.userId;
	}

	public int getSession() {
		return this.sessionId; 
	}

	public int getSection() {
		return this.sectionId;
	}

	public String getStatus() {
		return this.status;
	}

	public LocalDateTime getMigration() {
		return this.migrated;
	}


}