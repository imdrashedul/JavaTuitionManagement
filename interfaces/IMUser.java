package interfaces;

import java.sql.SQLException;
import library.DatabaseException;
import java.math.BigInteger;

import entity.*;

public interface IMUser {
	public int insert(User user) throws DatabaseException, SQLException;
	public int delete(User user) throws DatabaseException, SQLException;
	public int update(User user) throws DatabaseException, SQLException;
	public User retrive(BigInteger userid) throws DatabaseException, SQLException;
	public User retrive(Email email) throws DatabaseException, SQLException;
}