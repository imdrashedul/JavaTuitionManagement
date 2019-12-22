package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;
import library.*;
import entity.*;
import interfaces.*;
import models.*;

public class MFees implements IMFees {

	private Database db;
	private final String table;

	public MFees() {
		db = new Database();
		table = Configuration.TABLE_FEES;
	}

	public int insert(Fees fees) throws DatabaseException, SQLException {

		HashMap<String, Object> data = new HashMap<String, Object>();

		data.put("type_id", fees.getTypeId());
		data.put("section_id", fees.getSectionId());
		data.put("ammount", fees.getAmount());
		data.put("comment", fees.getComment());
		data.put("created", fees.getCreated());


		int lastid = 0;
		db.connect();
		lastid = db.insert(table, data);
		db.disconnect();

		return lastid;
	}

	

	public int delete(Fees fees) throws DatabaseException, SQLException {
		HashMap<String, Object> where = new HashMap<String, Object>();

		where.put("id", fees.getId());

		int rowAffect = 0;

		db.connect();
		rowAffect = db.remove(table, where);
		db.disconnect();

		return rowAffect;
	}

	public int update(Fees fees) throws DatabaseException, SQLException {
		HashMap<String, Object> update = new HashMap<String, Object>();
		HashMap<String, Object> where = new HashMap<String, Object>();

		update.put("type_id", fees.getTypeId());
		update.put("section_id", fees.getSectionId());
		update.put("ammount", fees.getAmount());
		update.put("comment", fees.getComment());
		update.put("created", fees.getCreated());

		where.put("id", fees.getId());

		int rowAffect = 0;
		db.connect();
		rowAffect = db.update(table, update, where);
		db.disconnect();

		return rowAffect;
	}

	public Fees retrive(int id) throws DatabaseException, SQLException {
		ArrayList<Object> values = new ArrayList<Object>();

		Fees fees = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `id` = ?, 'naem' = ? ORDER BY `id` DESC LIMIT 0, 1"; 
		values.add(id);
		

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			fees = new Fees(
				result.getInt("id"),
				result.getInt("type_id"),
				result.getInt("section_id"),
				result.getString("comment")
				result.getFloat("ammount"),
				result.getTimestamp("created").toLocalDateTime()
				);
		}

		db.disconnect();

		return fees;
	}

	public Fees retrive(int typeId, int sectionId) throws DatabaseException, SQLException{

		ArrayList<Object> values = new ArrayList<Object>();

		Fees fees = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `type_id` = ?, 'section_id' = ? ORDER BY `id` DESC LIMIT 0, 1"; 
		values.add(typeId);
		values.add(sectionId);

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			fees = new Fees(
			result.getInt("type_id"),
			result.getString("section_id")
			);
		}

		db.disconnect();

		return fees;

	}

	public Fees retrive(String comment, float amount) throws DatabaseException, SQLException{
		ArrayList<Object> values = new ArrayList<Object>();

		Fees fees = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `comment` = ?, 'amount' = ? ORDER BY `id` DESC LIMIT 0, 1"; 
		values.add(comment);
		values.add(amount);

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			fees = new Fees(
			result.getFloat("ammount"),
			result.getString("comment")
			);
		}

		db.disconnect();

		return fees;

	}

	

	}