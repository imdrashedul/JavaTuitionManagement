package interfaces;

import java.sql.SQLException;
import library.DatabaseException;
import java.math.BigInteger;

import entity.*;
import library.JcbItem;
import java.util.HashMap;

public interface IMClasses {
	public int insert(Classes classes) throws DatabaseException, SQLException;
	public int delete(Classes classes) throws DatabaseException, SQLException;
	public int update(Classes classes) throws DatabaseException, SQLException;
	public Classes retrive(int id) throws DatabaseException, SQLException;
	public Classes retrive(Integer numericName) throws DatabaseException, SQLException;
	public Classes retrive(String alphaName) throws DatabaseException, SQLException;
	public HashMap<Integer, JcbItem<Classes>> retrive() throws DatabaseException, SQLException;
}