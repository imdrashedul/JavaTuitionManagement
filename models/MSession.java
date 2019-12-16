package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;
import library.*;
import entity.*;
import interfaces.*;

public class MSession implements IMSession {

	private Database db;
	private final String table;

	public MSession() {
		db = new Database();
		table = Configuration.TABLE_SESSION;
	}

	public int insert(Session session) throws DatabaseException, SQLException {

		HashMap<String, Object> data = new HashMap<String, Object>();

		data.put("start", session.getStart());
		data.put("end", session.getEnd());
		data.put("created", session.getCreated());

		int lastid = 0;
		db.connect();
		lastid = db.insert(table, data);
		db.disconnect();

		return lastid;
	}

	public int delete(Session session) throws DatabaseException, SQLException {
		HashMap<String, Object> where = new HashMap<String, Object>();

		where.put("id", session.getId());

		int rowAffect = 0;

		db.connect();
		rowAffect = db.remove(table, where);
		db.disconnect();

		return rowAffect;
	}

	public int update(Session session) throws DatabaseException, SQLException {
		HashMap<String, Object> update = new HashMap<String, Object>();
		HashMap<String, Object> where = new HashMap<String, Object>();

		update.put("start", session.getPassHash());
		update.put("end", session.getRole());
		update.put("created", session.getRegistered());

		where.put("id", session.getId());

		int rowAffect = 0;
		db.connect();
		rowAffect = db.update(table, update, where);
		db.disconnect();

		return rowAffect;
	}

	public Session retrive(int sessionid) throws DatabaseException, SQLException {
		ArrayList<Object> values = new ArrayList<Object>();

		Session session = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `id` = ? ORDER BY `id` DESC LIMIT 0, 1"; 
		values.add(sessionid);

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			session = new Session(
				int.valueOf(result.getInt("id")),
				result.getint("start"),
				result.getint("end"),
				result.getTimestamp("created").toLocalDateTime()
			);
		}

		return session;
	}

	public Session retrive(int start, int end) throws DatabaseException, SQLException {
		ArrayList<Object> values = new ArrayList<Object>();

		Session session = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `start` = ? && 'end' = ? ORDER BY `id` DESC LIMIT 0, 1"; 
		values.add(email.get());

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			session = new Session(
				int.valueOf(result.getInt("id")),
				result.getint("start"),
				result.getint("end"),
				result.getTimestamp("created").toLocalDateTime()
			);
		}

		return session;
	}
}