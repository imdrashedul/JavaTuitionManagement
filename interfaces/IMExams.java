package interfaces;

import java.sql.SQLException;
import library.DatabaseException;
import java.math.BigInteger;



import entity.*;

public interface IMExams {
	public int insert(Exams exams) throws DatabaseException, SQLException;
	public int delete(Exams exams) throws DatabaseException, SQLException;
	public int update(Exams exams) throws DatabaseException, SQLException;
	public Exams retrive(BigInteger id) throws DatabaseException, SQLException;
	public Exams retrive(int classId, int sessionId, int sectionId) throws DatabaseException, SQLException;
	public Exams retrive(float mcqTotal, float examTotal, float writtenTotal) throws DatabaseException, SQLException;
	

	
}