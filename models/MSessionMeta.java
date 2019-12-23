package models;

import java.sql.ResultSet;
import java.math.BigInteger;
import java.sql.SQLException;

import java.util.*;
import library.*;
import entity.*;
import interfaces.*;

public class MSessionMeta implements IMSessionMeta {
	private Database db;
	private final String table;

	public MSessionMeta() {
		db = new Database();
		table = Configuration.TABLE_SESSION_DATA;
	}

	public int insert(SessionMeta sessionMeta) throws DatabaseException, SQLException {

		HashMap<String, Object> data = new HashMap<String, Object>();

		data.put("session_id", sessionMeta.getSession());
		data.put("section_id", sessionMeta.getSection());
		data.put("user_id", sessionMeta.getUser());

		int lastid = 0;
		db.connect();
		lastid = db.insert(table, data);
		db.disconnect();

		return lastid;
	}

	public int delete(SessionMeta sessionMeta) throws DatabaseException, SQLException {
		HashMap<String, Object> where = new HashMap<String, Object>();

		where.put("roll", sessionMeta.getRoll());

		int rowAffect = 0;

		db.connect();
		rowAffect = db.remove(table, where);
		db.disconnect();

		return rowAffect;
	}

	public int update(SessionMeta sessionMeta) throws DatabaseException, SQLException {
		HashMap<String, Object> update = new HashMap<String, Object>();
		HashMap<String, Object> where = new HashMap<String, Object>();

		update.put("session_id", sessionMeta.getSession());
		update.put("section_id", sessionMeta.getSection());
		update.put("user_id", sessionMeta.getUser());
		update.put("status", sessionMeta.getStatus());
		update.put("migrated", sessionMeta.getMigration());

		where.put("roll", sessionMeta.getRoll());

		int rowAffect = 0;
		db.connect();
		rowAffect = db.update(table, update, where);
		db.disconnect();

		return rowAffect;
	}

	public SessionMeta retrive(BigInteger roll) throws DatabaseException, SQLException {
		ArrayList<Object> values = new ArrayList<Object>();

		SessionMeta sessionMeta = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `roll` = ? ORDER BY `roll` DESC LIMIT 0, 1"; 
		values.add(roll);

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			sessionMeta = new SessionMeta(
				BigInteger.valueOf(result.getInt("roll")),
				result.getInt("session_id"),
				result.getInt("section_id"),
				BigInteger.valueOf(result.getInt("user_id")),
				result.getString("status"),
				result.getTimestamp("migrated")!=null ? result.getTimestamp("migrated").toLocalDateTime() : null
			);
		}

		db.disconnect();

		return sessionMeta;
	}

	public ArrayList<SessionMeta> retrive() throws DatabaseException, SQLException {
		ArrayList<SessionMeta> sessionMetas = new ArrayList<SessionMeta>();

		String query = "SELECT a.* FROM `"+db.getTable(table)+"` a INNER JOIN `"+db.getTable(Configuration.TABLE_SESSION)+"` b ON a.session_id = b.id WHERE b.cached = '1' AND a.status = '0' ORDER BY a.roll DESC"; 

		db.connect();
		ResultSet result = db.query(query);

		while(result.next()) {
			sessionMetas.add(new SessionMeta(
				BigInteger.valueOf(result.getInt("roll")),
				result.getInt("session_id"),
				result.getInt("section_id"),
				BigInteger.valueOf(result.getInt("user_id")),
				result.getString("status"),
				result.getTimestamp("migrated")!=null ? result.getTimestamp("migrated").toLocalDateTime() : null
			));
		}

		db.disconnect();

		return sessionMetas;

	}
}