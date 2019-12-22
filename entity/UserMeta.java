package entity;

import java.time.LocalDateTime;
import java.math.BigInteger;


public class UserMeta {
	
	private BigInteger id;
	private BigInteger userId;
	private String dataKey;
	private String dataValue;

	public UserMeta() {}

	public UserMeta(BigInteger id, BigInteger userId, String dataKey, String dataValue) {
		this.id = id;
		this.userId = userId;
		this.dataKey = dataKey;
		this.dataValue = dataValue;
	}

	public void setUser(BigInteger userId) {
		this.userId = userId;
	}

	public void setKey(String dataKey) {
		this.dataKey = dataKey; 
	}

	public void setValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public BigInteger getId() {
		return this.id;
	}

	public BigInteger getUserId() {
		return this.userId;
	}

	public String getKey() {
		return this.dataKey;
	}

	public String getValue() {
		return this.dataValue;
	}
}