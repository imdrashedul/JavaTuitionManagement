package models;

import java.sql.ResultSet;
import java.math.BigInteger;
import java.sql.SQLException;

import java.util.*;
import library.*;
import entity.*;
import interfaces.*;

public class MUserMeta implements IMUserMeta {

	private Database db;
	private final String table;

	public MUserMeta() {
		db = new Database();
		table = Configuration.TABLE_USERS_DATA;
	}

	public int insert(UserMeta userMeta) throws DatabaseException, SQLException {

		HashMap<String, Object> data = new HashMap<String, Object>();

		data.put("user_id", userMeta.getUserId());
		data.put("data_key", userMeta.getKey());
		data.put("data_value", userMeta.getValue());

		int lastid = 0;
		db.connect();
		lastid = db.insert(table, data);
		db.disconnect();

		return lastid;
	}

	public int insert(ArrayList<UserMeta> userMetas) throws DatabaseException, SQLException {
		ArrayList<String> columns = new ArrayList<String>();
		columns.add("user_id");
		columns.add("data_key");
		columns.add("data_value");

		ArrayList<ArrayList<Object>> rows = new ArrayList<ArrayList<Object>>();

		for(UserMeta meta:userMetas) {
			ArrayList<Object> row = new ArrayList<Object>();
			row.add(meta.getUserId());
			row.add(meta.getKey());
			row.add(meta.getValue());
			rows.add(row);
		}

		int rowAffect = 0;

		db.connect();
		rowAffect = db.insert(table, columns, rows);
		db.disconnect();

		return rowAffect;

	}

	public int delete(UserMeta userMeta) throws DatabaseException, SQLException {
		HashMap<String, Object> where = new HashMap<String, Object>();

		where.put("id", userMeta.getId());

		int rowAffect = 0;

		db.connect();
		rowAffect = db.remove(table, where);
		db.disconnect();

		return rowAffect;
	}

	public int update(UserMeta userMeta) throws DatabaseException, SQLException {
		HashMap<String, Object> update = new HashMap<String, Object>();
		HashMap<String, Object> where = new HashMap<String, Object>();

		update.put("user_id", userMeta.getUserId());
		update.put("data_key", userMeta.getKey());
		update.put("data_value", userMeta.getValue());

		where.put("id", userMeta.getId());

		int rowAffect = 0;
		db.connect();
		rowAffect = db.update(table, update, where);
		db.disconnect();

		return rowAffect;
	}

	public UserMeta retrive(BigInteger id) throws DatabaseException, SQLException {
		ArrayList<Object> values = new ArrayList<Object>();

		UserMeta userMeta = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `id` = ? ORDER BY `id` DESC LIMIT 0, 1"; 
		values.add(id);

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			userMeta = new UserMeta(
				BigInteger.valueOf(result.getInt("id")),
				BigInteger.valueOf(result.getInt("user_id")),
				result.getString("data_key"),
				result.getString("data_value")
			);
		}

		db.disconnect();

		return userMeta;
	}

	public UserMeta retrive(BigInteger userId, String dataKey) throws DatabaseException, SQLException {
		ArrayList<Object> values = new ArrayList<Object>();

		UserMeta userMeta = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `user_id` = ? AND `data_key` = ? ORDER BY `id` DESC LIMIT 0, 1"; 
		values.add(userId);
		values.add(dataKey);

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			userMeta = new UserMeta(
				BigInteger.valueOf(result.getInt("id")),
				BigInteger.valueOf(result.getInt("user_id")),
				result.getString("data_key"),
				result.getString("data_value")
			);
		}

		db.disconnect();

		return userMeta;
	}

	public HashMap<String, UserMeta> retrive(User user) throws DatabaseException, SQLException {
		HashMap<String, UserMeta> meta = new HashMap<String, UserMeta>();
		ArrayList<Object> values = new ArrayList<Object>();

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `user_id` = ? ORDER BY `id` DESC";
		values.add(user.getId());

		db.connect();
		ResultSet result = db.prepare(query, values);

		while(result.next()) {
			meta.put(result.getString("data_key"), new UserMeta(
				BigInteger.valueOf(result.getInt("id")),
				BigInteger.valueOf(result.getInt("user_id")),
				result.getString("data_key"),
				result.getString("data_value")
			));
		}

		db.disconnect();

		return meta;
	}
}