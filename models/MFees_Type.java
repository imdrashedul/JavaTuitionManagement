package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;
import library.*;
import entity.*;
import interfaces.*;

public class MFees_Type implements IMFees_Type {

	private Database db;
	private final String table;

	public MFees_Type() {
		db = new Database();
		table = Configuration.TABLE_FEES_TYPE;
	}

	public int insert(Fees_Type fees_type) throws DatabaseException, SQLException {

		HashMap<String, Object> data = new HashMap<String, Object>();

		data.put("name", fees_type.getName());

		int lastid = 0;
		db.connect();
		lastid = db.insert(table, data);
		db.disconnect();

		return lastid;
	}

	

	public int delete(Fees_Type fees_type) throws DatabaseException, SQLException {
		HashMap<String, Object> where = new HashMap<String, Object>();

		where.put("id", fees_type.getId());

		int rowAffect = 0;

		db.connect();
		rowAffect = db.remove(table, where);
		db.disconnect();

		return rowAffect;
	}

	public int update(Fees_Type fees_type) throws DatabaseException, SQLException {
		HashMap<String, Object> update = new HashMap<String, Object>();
		HashMap<String, Object> where = new HashMap<String, Object>();

		update.put("name", fees_type.getName());

		where.put("id", exams.getId());

		int rowAffect = 0;
		db.connect();
		rowAffect = db.update(table, update, where);
		db.disconnect();

		return rowAffect;
	}

	public Fees_Type retrive(int id, String name) throws DatabaseException, SQLException {
		ArrayList<Object> values = new ArrayList<Object>();

		Fees_Type fees_type = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `id` = ?, 'naem' = ? ORDER BY `id` DESC LIMIT 0, 1"; 
		values.add(id);
		values.add(name);

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			fees_type = new Fees_Type(
				result.getInt("id"),
				result.getString("name")
				);
		}

		db.disconnect();

		return fees_type;
	}

	

	}