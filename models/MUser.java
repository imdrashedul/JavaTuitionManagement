package models;

import java.sql.ResultSet;
import java.math.BigInteger;
import java.sql.SQLException;

import java.util.*;
import library.*;
import entity.*;
import interfaces.*;

public class MUser implements IMUser {

	private Database db;
	private final String table;

	public MUser() {
		db = new Database();
		table = Configuration.TABLE_USERS;
	}

	public int insert(User user) throws DatabaseException, SQLException {

		HashMap<String, Object> data = new HashMap<String, Object>();

		data.put("email", user.getEmail().get());
		data.put("password", user.getPassHash());
		data.put("role", user.getRole());
		data.put("registered", user.getRegistered());

		int lastid = 0;
		db.connect();
		lastid = db.insert(table, data);
		db.disconnect();

		return lastid;
	}

	public int delete(User user) throws DatabaseException, SQLException {
		HashMap<String, Object> where = new HashMap<String, Object>();

		where.put("id", user.getId());

		int rowAffect = 0;

		db.connect();
		rowAffect = db.remove(table, where);
		db.disconnect();

		return rowAffect;
	}

	public int update(User user) throws DatabaseException, SQLException {
		HashMap<String, Object> update = new HashMap<String, Object>();
		HashMap<String, Object> where = new HashMap<String, Object>();

		update.put("email", user.getEmail());
		update.put("password", user.getPassHash());
		update.put("role", user.getRole());
		update.put("registered", user.getRegistered());

		where.put("id", user.getId());

		int rowAffect = 0;
		db.connect();
		rowAffect = db.update(table, update, where);
		db.disconnect();

		return rowAffect;
	}

	public User retrive(BigInteger userid) throws DatabaseException, SQLException {
		ArrayList<Object> values = new ArrayList<Object>();

		User user = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `id` = ? ORDER BY `id` DESC LIMIT 0, 1"; 
		values.add(userid);

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			user = new User(
				BigInteger.valueOf(result.getInt("id")),
				new Email(result.getString("email")),
				result.getString("password"),
				result.getString("role"),
				result.getTimestamp("registered").toLocalDateTime()
			);
		}

		return user;
	}

	public User retrive(Email email) throws DatabaseException, SQLException {
		ArrayList<Object> values = new ArrayList<Object>();

		User user = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `email` = ? ORDER BY `id` DESC LIMIT 0, 1"; 
		values.add(email.get());

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			user = new User(
				BigInteger.valueOf(result.getInt("id")),
				new Email(result.getString("email")),
				result.getString("password"),
				result.getString("role"),
				result.getTimestamp("registered").toLocalDateTime()
			);
		}

		return user;
	}
}