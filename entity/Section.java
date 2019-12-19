package entity;

import java.math.BigInteger;

public class Section {
	
	private BigInteger id;
	private BigInteger classId;
	private String alphaName;


	public Section() {}

	public Section(BigInteger id , BigInteger classId , String alphaName ) {
		this.id = id;
		this.classId = classId;
		this.alphaName = alphaName;
		
	}

	public void setClassId(BigInteger classId) {
		this.classId = classId; 
	}

	public void setAlphaName(String alphaName) {
		this.alphaName = alphaName;
	}

	public BigInteger getId() {
		return this.id;
	}

	public BigInteger getClassId() {
		return this.classId;
	}

	public String getAlphaName() {
		return this.alphaName;
	}
