package entity;

import java.time.LocalDateTime;
import java.math.BigInteger;



public class Exams {
	
	private BigInteger id;
	private int classId;
	private int sessionId;
	private int sectionId;
	private String name;
	private LocalDateTime examDate;
	private String examType;
	private float mcqTotal;
	private float writtenTotal;
	private float examTotal;


	public Exams() {}

	public Exams(BigInteger id , int classId, int sessionId, int sectionId, String name, LocalDateTime examDate, String examType, float mcqTotal, float writtenTotal, float examTotal ) {
		this.id = id;
		this.classId = classId;
		this.sessionId = sessionId;
		this.sectionId = sectionId;
		this.name = name;
		this.examDate = examDate;
		this.examType = examType;
		this.examTotal = examTotal;
		this.mcqTotal = mcqTotal;
		this.writtenTotal = writtenTotal;
		
	}

	public void setClassId(int classId) {
		this.classId = classId; 
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public void setSessionId(int sessionId){
		this.sessionId = sessionId;

	}
	public void setExamDate(LocalDateTime examDate){
		this.examDate = examDate;

	}

	public void setExamTotal(float examTotal){
		this.examTotal = examTotal;

	}

	public void setMcqTotal(float mcqTotal){
		this.mcqTotal = mcqTotal;

	}

	public void setWrittenTotal(float writtenTotal){
		this.writtenTotal = writtenTotal;

	}

	public void setExamType(String examType){
		this.examType = examType;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public int getClassId() {
		return this.classId;
	}

	public int getSectionId() {
		return this.sectionId;
	}

	public int getSessionId() {
		return this.sessionId;
	}

	public float getExamTotal() {
		return this.examTotal;
	}

	public float getMcqTotal() {
		return this.mcqTotal;
	}

	public float getWrittenTotal() {
		return this.writtenTotal;
	}

	public String getaName() {
		return this.name;
	}

	public String getExamType(){
		return this.examType;

	}
	public LocalDateTime getExamDate(){
		return this.examDate;
	}
}
