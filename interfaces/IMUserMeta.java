package interfaces;

import java.sql.SQLException;
import library.DatabaseException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.ArrayList;

import entity.*;

public interface IMUserMeta {
	public int insert(UserMeta userMeta) throws DatabaseException, SQLException;
	public int insert(ArrayList<UserMeta> userMetas) throws DatabaseException, SQLException;
	public int delete(UserMeta userMeta) throws DatabaseException, SQLException;
	public int update(UserMeta userMeta) throws DatabaseException, SQLException;
	public HashMap<String, UserMeta> retrive(User user) throws DatabaseException, SQLException;
	public UserMeta retrive(BigInteger userId, String dataKey) throws DatabaseException, SQLException;
	public UserMeta retrive(BigInteger id) throws DatabaseException, SQLException;
}