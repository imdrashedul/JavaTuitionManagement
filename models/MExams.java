package models;

import java.lang.*;
import java.sql.ResultSet;
import java.math.BigInteger;
import java.sql.SQLException;

import java.util.*;
import library.*;
import entity.*;
import interfaces.*;
import models.*;

public class MExams implements IMExams {

	private Database db;
	private final String table;

	public MExams() {
		db = new Database();
		table = Configuration.TABLE_EXAMS;
	}

	public int insert(Exams exams) throws DatabaseException, SQLException {

		HashMap<String, Object> data = new HashMap<String, Object>();

		data.put("class_id", exams.getClassId());
		data.put("section_id", exams.getSectionId());
		data.put("session_id", exams.getSessionId());
		data.put("name", exams.getName());
		data.put("exam_type", exams.getExamType());
		data.put("exam_total", exams.getExamTotal());
		data.put("exam_date", exams.getExamDate());
		data.put("mcq_total", exams.getMcqTotal());
		data.put("written_total", exams.getWrittenTotal());

		int lastid = 0;
		db.connect();
		lastid = db.insert(table, data);
		db.disconnect();

		return lastid;
	}

	

	public int delete(Exams exams) throws DatabaseException, SQLException {
		HashMap<String, Object> where = new HashMap<String, Object>();

		where.put("id", exams.getId());

		int rowAffect = 0;

		db.connect();
		rowAffect = db.remove(table, where);
		db.disconnect();

		return rowAffect;
	}

	public int update(Exams exams) throws DatabaseException, SQLException {
		HashMap<String, Object> update = new HashMap<String, Object>();
		HashMap<String, Object> where = new HashMap<String, Object>();

		update.put("class_id", exams.getClassId());
		update.put("section_id", exams.getSectionId());
		update.put("session_id", exams.getSessionId());
		update.put("name", exams.getName());
		update.put("exam_type", exams.getExamType());
		update.put("exam_total", exams.getExamTotal());
		update.put("exam_date", exams.getExamDate());
		update.put("mcq_total", exams.getMcqTotal());
		update.put("written_total", exams.getWrittenTotal());

		where.put("id", exams.getId());

		int rowAffect = 0;
		db.connect();
		rowAffect = db.update(table, update, where);
		db.disconnect();

		return rowAffect;
	}

	public Exams retrive(BigInteger id) throws DatabaseException, SQLException {
		ArrayList<Object> values = new ArrayList<Object>();

		Exams exams = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `id` = ? ORDER BY `id` DESC LIMIT 0, 1"; 
		values.add(id);

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			exams = new Exams(
				BigInteger.valueOf(result.getInt("id")),
				result.getInt("class_id"),
				result.getInt("section_id"),
				result.getInt("session_id"),
				result.getTimestamp("exam_date").toLocalDateTime(),
				result.getfloat("exam_total"),
				result.getfloat("mcq_total"),
				result.getfloat("written_total"),
				result.getString("name"),
				result.getString("exam_type")
				);
		}

		db.disconnect();

		return exams;
	}

	public Exams retrive(int classId, int sessionId, int sectionId) throws DatabaseException, SQLException {
		ArrayList<Object> values = new ArrayList<Object>();

		Exams exams = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `class_id` = ? , `section_id` = ? , `session_id` = ? ORDER BY `id` DESC LIMIT 0, 1"; 
		values.add(classId);
		values.add(sectionId);
		values.add(sessionId);

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			exams = new Exams(
				BigInteger.valueOf(result.getInt("id")),
				result.getInt("class_id"),
				result.getInt("section_id"),
				result.getInt("session_id")
			);
		}

		db.disconnect();

		return exams;
	}

	public Exams retrive(float mcqTotal, float examTotal, float writtenTotal) throws DatabaseException, SQLException{
		ArrayList<Object> values = new ArrayList<Object>();

		Exams exams = null;

		String query = "SELECT * FROM `"+db.getTable(table)+"` WHERE `mcq_total` = ? or `written_total` = ? or `exam_total` = ? ORDER BY `id` DESC LIMIT 0, 1"; 
		values.add(mcqTotal);
		values.add(examTotal);
		values.add(writtenTotal);

		db.connect();
		ResultSet result = db.prepare(query, values);

		if(result.next()) {
			exams = new Exams(
			BigInteger.valueOf(result.getInt("id")),
			result.getFloat("mcq_total"),
			result.getFloat("written_total"),
			result.getFloat("exam_total")
			);
		}

		db.disconnect();

		return exams;

	}