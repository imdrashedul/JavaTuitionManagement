package interfaces;

import java.sql.SQLException;
import library.DatabaseException;
import java.util.HashMap;

import entity.*;
import library.JcbItem;

public interface IMSession {
	public int insert(Session session) throws DatabaseException, SQLException;
	public int delete(Session session) throws DatabaseException, SQLException;
	public int update(Session session) throws DatabaseException, SQLException;
	public Session retrive(int sessionid) throws DatabaseException, SQLException;
	public Session retrive(int start, int end) throws DatabaseException, SQLException;
	public HashMap<Integer, JcbItem<Session>> retrive() throws DatabaseException, SQLException;
}