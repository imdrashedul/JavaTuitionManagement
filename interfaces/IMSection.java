package interfaces;

import java.sql.SQLException;
import library.DatabaseException;
import java.math.BigInteger;

import entity.*;

public interface IMSection {
	public int insert(Section section) throws DatabaseException, SQLException;
	public int delete(Section section) throws DatabaseException, SQLException;
	public int update(Section section) throws DatabaseException, SQLException;
	public HashMap<String, Section> retrive(Classes class) throws DatabaseException, SQLException;
	public Classes retrive(BigInteger id) throws DatabaseException, SQLException;
	public Classes retrive(BigInteger classId, String alphaName) throws DatabaseException, SQLException;
	
}