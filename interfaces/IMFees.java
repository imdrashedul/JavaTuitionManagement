package interfaces.*;

import java.lang.*;
import java.sql.SQLException;
import library.DatabaseException;
import java.time.LocalDateTime;



import entity.*;


public interface IMFees {
	public int insert(Fees fees) throws DatabaseException, SQLException;
	public int delete(Fees fees) throws DatabaseException, SQLException;
	public int update(Fees fees) throws DatabaseException, SQLException;
	public Fees retrive(int id) throws DatabaseException, SQLException;
	public Fees retrive(int typeId, int sectionId) throws DatabaseException, SQLException;
	public Fees retrive(String comment, float amount) throws DatabaseException, SQLException;
	
	
	

	
}