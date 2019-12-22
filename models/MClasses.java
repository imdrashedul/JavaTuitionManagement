package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;
import library.*;
import entity.*;
import interfaces.*;

public class MClasses implements IMClasses {

	private Database db;
	private final String table;

	public MClasses () {
		db = new Database();
		table = Configuration.TABLE_CLASSES;
	}
	public int insert(Classes classes) throws DatabaseException, SQLException {
		
		HashMap<String, Object> data = new HashMap<String, Object>();

		data.put("num", classes.getNumericName());
		data.put("name", classes.getAlphaName());
		

		int lastid = 0;
		db.connect();
		lastid = db.insert(table, data);
		db.disconnect();

		return lastid;
		
	}
	public int delete(Classes classes) throws DatabaseException, SQLException {
		
		HashMap<String, Object> where = new HashMap<String, Object>();

		where.put("id", classes.getId());

		int rowAffect = 0;

		db.connect();
		rowAffect = db.remove(table, where);
		db.disconnect();

		return rowAffect;
		
	}
	public int update(Classes classes) throws DatabaseException, SQLException {
		
		HashMap<String, Object> update = new HashMap<String, Object>();
		HashMap<String, Object> where = new HashMap<String, Object>();

		update.put("num", classes.getNumericName());
		update.put("name", classes.getAlphaName());
		

		where.put("id", classes.getId());

		int rowAffect = 0;
		db.connect();
		rowAffect = db.update(table, update, where);
		db.disconnect();

		return rowAffect;
		
	}
	public Classes retrive(int id) throws DatabaseException, SQLException {
		
		ArrayList<Object> values = new ArrayList<Object>();

		Classes classes = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `id` = ? ORDER BY `id` DESC LIMIT 0, 1"; 
		values.add(id);

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			classes = new Classes(
				result.getInt("id"),
				result.getInt("num"),
				result.getString("name")
			);
		}

		return classes;
		
	}
	public Classes retrive(Integer numericName) throws DatabaseException, SQLException {
		
		ArrayList<Object> values = new ArrayList<Object>();

		Classes classes = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `num` = ? ORDER BY `id` DESC LIMIT 0, 1"; 
		values.add(numericName.intValue());

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			classes = new Classes(
				result.getInt("id"),
				result.getInt("num"),
				result.getString("name")
			);
		}

		return classes;
		
	}

	public Classes retrive(String alphaName) throws DatabaseException, SQLException {
		
		ArrayList<Object> values = new ArrayList<Object>();

		Classes classes = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `name` = ? ORDER BY `id` DESC LIMIT 0, 1"; 
		values.add(alphaName);

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			classes = new Classes(
				result.getInt("id"),
				result.getInt("num"),
				result.getString("name")
			);
		}

		return classes;
		
	}

	public HashMap<Integer, JcbItem<Classes>> retrive() throws DatabaseException, SQLException {
		HashMap<Integer, JcbItem<Classes>> classes = new HashMap<Integer, JcbItem<Classes>>();

		String query = "SELECT * FROM `"+db.getTable(table)+"` ORDER BY `id` DESC";

		db.connect();
		ResultSet result = db.query(query);

		while(result.next()) {
			Classes _class = new Classes(
				result.getInt("id"),
				result.getInt("num"),
				result.getString("name")
			);
			classes.put(_class.getId(), new JcbItem<Classes>( _class.getAlphaName(), _class ));
		}

		db.disconnect();

		return classes;
	}

}