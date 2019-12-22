package interfaces.*;

import java.sql.SQLException;
import library.DatabaseException;



import entity.*;

public interface IMFees_Type {
	public int insert(Fees_Type fees_type) throws DatabaseException, SQLException;
	public int delete(Fees_Type fees_type) throws DatabaseException, SQLException;
	public int update(Fees_Type fees_type) throws DatabaseException, SQLException;
	public Fees_Type retrive(int id) throws DatabaseException, SQLException;
	
	
	

	
}