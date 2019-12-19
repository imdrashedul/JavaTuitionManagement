package entity;

public class Section {
	
	private int id;
	private int classId;
	private String alphaName;


	public Section() {}

	public Section(int id , int classId , String alphaName ) {
		this.id = id;
		this.classId = classId;
		this.alphaName = alphaName;
		
	}

	public void setClassId(int classId) {
		this.classId = classId; 
	}

	public void setAlphaName(String alphaName) {
		this.alphaName = alphaName;
	}

	public int getId() {
		return this.id;
	}

	public int getClassId() {
		return this.classId;
	}

	public String getAlphaName() {
		return this.alphaName;
	}
}