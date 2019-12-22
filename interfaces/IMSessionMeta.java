package interfaces;

import java.sql.SQLException;
import library.DatabaseException;
import java.math.BigInteger;

import entity.*;

public interface IMSessionMeta {
	public int insert(SessionMeta sessionMeta) throws DatabaseException, SQLException;
	public int delete(SessionMeta sessionMeta) throws DatabaseException, SQLException;
	public int update(SessionMeta sessionMeta) throws DatabaseException, SQLException;
	public SessionMeta retrive(BigInteger roll) throws DatabaseException, SQLException;
}