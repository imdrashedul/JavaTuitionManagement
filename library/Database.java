package library;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import java.util.*;
import library.json.*;
import library.json.parser.*;
import library.apachelang3.StringUtils;

public class Database {

	private String hostname;
	private String port;
	private String username;
	private String password;
	private String database;
	private String prefix;

	private Connection connection;
	private Statement statement;
	private PreparedStatement prepared;
	private ResultSet result;

	public Database() {
		JSONObject info = (JSONObject) collectInfo(Configuration.DB_FILE);
		
		if(info!=null) {
			this.hostname = info.get("db_host").toString();
			this.port = info.get("db_port").toString();
			this.username = info.get("db_user").toString();
			this.password = info.get("db_pass").toString();
			this.database = info.get("db_name").toString();
			this.prefix = Configuration.DB_PREFIX;
		}
		
	}

	public Database(String database, String username, String password, String prefix) {
		this.hostname = "127.0.0.1";
		this.port = "3306";
		this.username = username;
		this.password = password;
		this.database = database;
		this.prefix = prefix;
	}

	public Database(String database, String username, String password, String prefix, String hostname, String port) {
		this.hostname = hostname;
		this.port = port;
		this.username = username;
		this.password = password;
		this.database = database;
		this.prefix = prefix;
	}

	private JSONObject collectInfo(String path) {
		
		JSONObject json = null;

		try {
			FileManager fileManager = new FileManager(path);
			String content = fileManager.read();
			if(!StringUtils.isEmpty(content)) {
				String decrpyted = EncManager.decrypt(content, Configuration.ENC_SALT);
				if(!StringUtils.isEmpty(decrpyted)) {
					JSONParser parser = new JSONParser();
					json = (JSONObject) parser.parse(decrpyted);
				}
			}
		} catch(Exception ex) {}

		return json;
	}

	public void connect() throws DatabaseException {
		String url = "jdbc:mysql://"+this.hostname+":"+this.port+"/"+this.database;
		try  {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(url, this.username, this.password);
		} catch(SQLException | ClassNotFoundException ex) {
			this.connection = null;
			throw new DatabaseException("Could not connect to the database: " + ex.getMessage());
		}
	}

	public void disconnect() throws DatabaseException {
		try {
			this.connection.close();
			
			if(this.statement!=null) {
				this.statement.close();
				this.statement = null;
			}

			if(this.result!=null) {
				this.result.close();
				this.result = null;
			}

			if(this.prepared!=null) {
				this.prepared.close();
				this.prepared = null;
			}

		} catch (SQLException ex) {
			this.connection = null;
			throw new DatabaseException("Connection Error: " + ex.getMessage());
		}
		this.connection = null;
	}

	public ResultSet prepare(String query, ArrayList<Object> values) throws DatabaseException, SQLException {

		if(!this.isConnected()) this.connect();
		
		prepared = this.connection.prepareStatement(query);

		for(int i = 1; i <= values.size(); i++) {
			prepared.setObject(i, values.get(i-1));
		}

		prepared.execute();
		
		return prepared.getResultSet();
	}

	public int insert(String table, HashMap<String, Object> data) throws DatabaseException, SQLException {
		if(!this.isConnected()) this.connect();

		int lastInsertId = 0;

		int columnTotal = data.size();

		String query = "";

		String maps = "";



		if(columnTotal>0) {

			query += "INSERT INTO `"+getTable(table)+"` ( ";
			maps += "( ";

			int i = 1;

			for(Map.Entry<String, Object> column:data.entrySet()) {
				if(i==columnTotal) {
					query += "`"+column.getKey()+"` ";
					maps += "? ";

				} else {
					query += "`"+column.getKey()+"`, ";
					maps += "?, ";
				}
				i++;
			}

			query += ") ";
			maps += ")";
			query += "VALUES ";
			query += maps;
		}

		if(!StringUtils.isEmpty(query)) {
			prepared = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			int j = 1;

			for(Map.Entry<String, Object> column:data.entrySet()) {
				prepared.setObject(j, column.getValue()); 
				j++;
			}

			if(prepared.executeUpdate()>0) {
				ResultSet rs = prepared.getGeneratedKeys();
				if(rs.next()) {
					lastInsertId = rs.getInt(1);
				}
			}
		}

		return lastInsertId;
	}

	public int update(String table, HashMap<String, Object> update, HashMap<String, Object> where) throws DatabaseException, SQLException {
		if(!this.isConnected()) this.connect();

		int rowsAffect = 0;

		int updateTotal = update.size();
		int whereTotal = where.size();

		String query = "";

		if(updateTotal>0 && whereTotal>0) {

			int i = 1;

			query += "UPDATE `"+getTable(table)+"` SET ";

			for(Map.Entry<String, Object> column:update.entrySet()) {

				query += i==updateTotal? "`"+column.getKey()+"` = ? " : "`"+column.getKey()+"` = ?, ";

				i++;
			}

			query += "WHERE ";
			i = 1;

			for(Map.Entry<String, Object> column:where.entrySet()) {
				query += i==whereTotal ? "`"+column.getKey()+"` = ? AND " : "`"+column.getKey()+"` = ? ";
				i++;
			}
		}

		if(!StringUtils.isEmpty(query)) {
			prepared = this.connection.prepareStatement(query);
			int j = 1;

			for(Map.Entry<String, Object> column:update.entrySet()) {
				prepared.setObject(j, column.getValue()); 
				j++;
			}
			
			for(Map.Entry<String, Object> column:where.entrySet()) {
				prepared.setObject(j, column.getValue()); 
				j++;
			}

			rowsAffect = prepared.executeUpdate();
		}

		return rowsAffect;
	}

	public int remove(String table, HashMap<String, Object> where) throws DatabaseException, SQLException {
		if(!this.isConnected()) this.connect();

		int rowsAffect = 0;
		int whereTotal = where.size();
		String query = "";

		if(whereTotal>0) {

			int i = 1;

			query += "DELETE FROM `"+getTable(table)+"` WHERE ";

			for(Map.Entry<String, Object> column:where.entrySet()) {
					query += i==whereTotal ? "`"+column.getKey()+"` = ? AND " : "`"+column.getKey()+"` = ? ";
				i++;
			}
		}

		if(!StringUtils.isEmpty(query)) {
			prepared = this.connection.prepareStatement(query);
			int j = 1;
			for(Map.Entry<String, Object> column:where.entrySet()) {
				prepared.setObject(j, column.getValue()); 
				j++;
			}
			rowsAffect = prepared.executeUpdate();
		}

		return rowsAffect;
	}

	public ResultSet query(String query) throws DatabaseException, SQLException {
		if(!this.isConnected()) this.connect();
		
		Statement statement = this.connection.createStatement();
		
		return statement.executeQuery(query);
	}
	
	public int updateQuery(String query) throws DatabaseException, SQLException {
		if(this.isConnected()) this.connect();
		
		Statement statement = this.connection.createStatement();
		
		return statement.executeUpdate(query);
	}

	public String getHostname() {
		return this.hostname;
	}
	
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	public String getPort() {
		return this.port;
	}
	
	public void setPort(String port) {
		this.port = port;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDatabase() {
		return this.database;
	}
	
	public void setDatabase(String database) {
		this.database = database;
	}
	
	public Connection getConnection() {
		return this.connection;
	}

	public String getTable(String table) {
		return this.prefix+table;
	}

	public boolean isConnected() {
		try {
			return this.connection != null && !this.connection.isClosed();
		} catch (SQLException e) {
			return false;
		}
	}

}