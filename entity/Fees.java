package entity;

import java.lang.*;
import java.time.LocalDateTime;
import java.math.BigInteger;



public class Fees {
	
	private int id;
	private int typeId;
	private int sectionId;
	private String comment;
	private LocalDateTime created;
	private float amount;


	public Fees() {}

	public Fees(int id , int typeId, int sectionId, String comment, LocalDateTime created,  float amount ) {
		this.id = id;
		this.typeId = typeId;
		this.sectionId = sectionId;
		this.comment = comment;
		this.created = created;
		this.amount = amount;
	}

	public void setTypeId(int type_id) {
		this.type_id = type_id; 
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public void setCreated(LocalDateTime created){
		this.created = created;

	}

	public void setAmount(float amount){
		this.amount = amount;

	}

	public void setComment(String comment){
		this.comment = comment;
	}



	public int getId() {
		return this.id;
	}

	public int getTypeId() {
		return this.typeId;
	}

	public int getSectionId() {
		return this.sectionId;
	}

	public float getAmount() {
		return this.amount;
	}

	public String getComment() {
		return this.comment;
	}

	public LocalDateTime getCreated(){
		return this.created;
	}
}
