package interfaces;

import java.sql.SQLException;
import library.DatabaseException;

import java.util.HashMap;
import java.util.ArrayList;


import entity.*;

public interface IMSection {
	public int insert(Section section) throws DatabaseException, SQLException;
	public int delete(Section section) throws DatabaseException, SQLException;
	public int update(Section section) throws DatabaseException, SQLException;
	public HashMap<String, Section> retrive(Classes classes) throws DatabaseException, SQLException;
	public Section retrive(int id) throws DatabaseException, SQLException;
	public Section retrive(int classId, String alphaName) throws DatabaseException, SQLException;
	
}