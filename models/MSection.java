package models;

import java.sql.ResultSet;
import java.math.BigInteger;
import java.sql.SQLException;

import java.util.*;
import library.*;
import entity.*;
import interfaces.*;

public class MSection implements IMSection {

	private Database db;
	private final String table;

	public MSection() {
		db = new Database();
		table = Configuration.TABLE_SECTION;
	}

	public int insert(Section section) throws DatabaseException, SQLException {

		HashMap<String, Object> data = new HashMap<String, Object>();

		data.put("class_id", section.getClassId());
		data.put("name", section.getAlphaName());

		int lastid = 0;
		db.connect();
		lastid = db.insert(table, data);
		db.disconnect();

		return lastid;
	}

	

	public int delete(Section section) throws DatabaseException, SQLException {
		HashMap<String, Object> where = new HashMap<String, Object>();

		where.put("id", section.getId());

		int rowAffect = 0;

		db.connect();
		rowAffect = db.remove(table, where);
		db.disconnect();

		return rowAffect;
	}

	public int update(Section section) throws DatabaseException, SQLException {
		HashMap<String, Object> update = new HashMap<String, Object>();
		HashMap<String, Object> where = new HashMap<String, Object>();

		update.put("class_id", section.getClassId());
		update.put("name", section.getAlphaName());

		where.put("id", section.getId());

		int rowAffect = 0;
		db.connect();
		rowAffect = db.update(table, update, where);
		db.disconnect();

		return rowAffect;
	}

	public Section retrive(int id) throws DatabaseException, SQLException {
		ArrayList<Object> values = new ArrayList<Object>();

		Section section = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `id` = ? ORDER BY `id` DESC LIMIT 0, 1"; 
		values.add(id);

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			section = new Section(
				result.getInt("id"),
				result.getInt("class_id"),
				result.getString("name")
				);
		}

		db.disconnect();

		return section;
	}

	public Section retrive(int classId, String alphaName) throws DatabaseException, SQLException {
		ArrayList<Object> values = new ArrayList<Object>();

		Section section = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `class_id` = ? AND `name` = ? ORDER BY `id` DESC LIMIT 0, 1"; 
		values.add(classId);
		values.add(alphaName);

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			section = new Section(
				result.getInt("id"),
				result.getInt("class_id"),
				result.getString("name")
			);
		}

		db.disconnect();

		return section;
	}

	public HashMap<String, Section> retrive(Classes classes) throws DatabaseException, SQLException {
		HashMap<String, Section> sec = new HashMap<String, Section>();
		ArrayList<Object> values = new ArrayList<Object>();

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `class_id` = ? ORDER BY `id` DESC";
		values.add(classes.getId());

		db.connect();
		ResultSet result = db.prepare(query, values);

		while(result.next()) {
			sec.put(result.getString("class_id"), new Section(
				result.getInt("id"),
				result.getInt("class_id"),
				result.getString("name")
			));
		}

		db.disconnect();

		return sec;
	}
}