package entity;



public class Fees_Type {
	
	private int id;
	private String name;
	

	public Fees_Type() {}

	public Fees_Type(int id , String name ) {
		this.id = id;
		this.name = name;
		
	}

	

	public void setName(String name){
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public String getaName() {
		return this.name;
	}

}
