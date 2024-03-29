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

		update.put("start", session.getStart());
		update.put("end", session.getEnd());
		update.put("cached", session.getCached());
		update.put("created", session.getCreated());

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
				result.getInt("id"),
				result.getInt("start"),
				result.getInt("end"),
				result.getString("cached"),
				result.getTimestamp("created").toLocalDateTime()
			);
		}

		return session;
	}

	public Session retrive(int start, int end) throws DatabaseException, SQLException {
		ArrayList<Object> values = new ArrayList<Object>();

		Session session = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `start` = ? && 'end' = ? ORDER BY `id` DESC LIMIT 0, 1"; 
		values.add(start);
		values.add(end);

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			session = new Session(
				result.getInt("id"),
				result.getInt("start"),
				result.getInt("end"),
				result.getString("cached"),
				result.getTimestamp("created").toLocalDateTime()
			);
		}
		db.disconnect();

		return session;
	}

	public HashMap<Integer, JcbItem<Session>> retrive() throws DatabaseException, SQLException {
		HashMap<Integer, JcbItem<Session>> sessions = new HashMap<Integer, JcbItem<Session>>();

		String query = "SELECT * FROM `"+db.getTable(table)+"` ORDER BY `id` DESC";

		db.connect();
		ResultSet result = db.query(query);

		while(result.next()) {
			Session session = new Session(
				result.getInt("id"),
				result.getInt("start"),
				result.getInt("end"),
				result.getString("cached"),
				result.getTimestamp("created").toLocalDateTime()
			);
			sessions.put(session.getId(), new JcbItem<Session>( Helper.getSessionStr(session), session ));
		}

		db.disconnect();

		return sessions;
	}

	public HashMap<Integer, JcbItem<Session>> retrive(boolean cached) throws DatabaseException, SQLException {
		HashMap<Integer, JcbItem<Session>> sessions = new HashMap<Integer, JcbItem<Session>>();

		String flaq = cached ? "1":"0";
		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `cached` = "+flaq+" ORDER BY `id` DESC";

		db.connect();
		ResultSet result = db.query(query);

		while(result.next()) {
			Session session = new Session(
				result.getInt("id"),
				result.getInt("start"),
				result.getInt("end"),
				result.getString("cached"),
				result.getTimestamp("created").toLocalDateTime()
			);
			sessions.put(session.getId(), new JcbItem<Session>( Helper.getSessionStr(session), session ));
		}

		db.disconnect();

		return sessions;
	}

	public ArrayList<Session> table(String cached) throws DatabaseException, SQLException {
		ArrayList<Session> sessions = new ArrayList<Session>();

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `cached` = "+cached+" ORDER BY `id` DESC";

		db.connect();
		ResultSet result = db.query(query);

		while(result.next()) {
			sessions.add(new Session(
				result.getInt("id"),
				result.getInt("start"),
				result.getInt("end"),
				result.getString("cached"),
				result.getTimestamp("created").toLocalDateTime()
			));
		}

		db.disconnect();

		return sessions;
	}

	public ArrayList<Session> table() throws DatabaseException, SQLException {
		ArrayList<Session> sessions = new ArrayList<Session>();

		String query = "SELECT * FROM `"+db.getTable(table)+"` ORDER BY `id` DESC";

		db.connect();
		ResultSet result = db.query(query);

		while(result.next()) {
			sessions.add(new Session(
				result.getInt("id"),
				result.getInt("start"),
				result.getInt("end"),
				result.getString("cached"),
				result.getTimestamp("created").toLocalDateTime()
			));
		}

		db.disconnect();

		return sessions;
	}
}