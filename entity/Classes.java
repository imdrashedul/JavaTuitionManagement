package entity;



public class Classes {
	
	private int id;
	private int numericName;
	private String alphaName;


	public Classes() {}

	public Classes(int id , int numericName , String alphaName ) {
		this.id = id;
		this.numericName = numericName;
		this.alphaName = alphaName;
		
	}

	public void setNumericName(int numericName) {
		this.numericName = numericName; 
	}

	public void setAlphaName(String alphaName) {
		this.alphaName = alphaName;
	}

	public int getId() {
		return this.id;
	}

	public int getNumericName() {
		return this.numericName;
	}

	public String getAlphaName() {
		return this.alphaName;
	}
}
