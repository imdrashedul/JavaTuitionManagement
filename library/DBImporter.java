package library;

import java.sql.SQLException;

public class DBImporter {
	
	private Database db;


	public DBImporter() {
		db = new Database();
	}

	public void importDb() throws DatabaseException, SQLException {
		db.connect();
		table_attendance();
		table_classes();
		table_exams();
		table_fees();
		table_fees_history();
		table_fees_type();
		table_grading();
		table_invoice();
		table_invoice_item();
		table_invoice_payment();
		table_marks();
		table_salary();
		table_section();
		table_session();
		table_session_data();
		table_users();
		table_users_data();
		table_waiver();
		table_relations();
		db.disconnect();
	}

	private void table_attendance() throws SQLException, DatabaseException {
		String query = "CREATE TABLE IF NOT EXISTS `"+db.getTable(Configuration.TABLE_ATTENDANCE)+"` (" +
						"`id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT," +
						"`roll_id` bigint(20) UNSIGNED NOT NULL," +
						"`date` date NOT NULL," +
						"`status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'A/P/L => Absent/Present/Late'," +
						"`comment` text COLLATE utf8mb4_unicode_ci NOT NULL," +
						"`taken_by` bigint(20) UNSIGNED NOT NULL," +
						"`taken_date` datetime NOT NULL," +
						"PRIMARY KEY (`id`)," +
						"KEY `taken_by` (`taken_by`)," +
						" KEY `roll_id` (`roll_id`)" +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		db.updateQuery(query);
	}

	private void table_classes() throws SQLException, DatabaseException {
		String query = "CREATE TABLE IF NOT EXISTS `"+db.getTable(Configuration.TABLE_CLASSES)+"` (" +
						"`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT," +
						"`num` int(10) UNSIGNED NOT NULL," +
						"`name` varchar(155) COLLATE utf8mb4_unicode_ci NOT NULL," +
						"PRIMARY KEY (`id`)" +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		db.updateQuery(query);
	}

	private void table_exams() throws SQLException, DatabaseException {
		String query = "CREATE TABLE IF NOT EXISTS `"+db.getTable(Configuration.TABLE_EXAMS)+"` (" +
						"`id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT," +
						"`session_id` int(10) UNSIGNED NOT NULL," +
						"`class_id` int(10) UNSIGNED DEFAULT NULL," +
						"`section_id` int(10) UNSIGNED DEFAULT NULL," +
						"`name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL," +
						"`exam_type` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'M/C => Model Test/Class Test'," +
						"`mcq_total` float UNSIGNED NOT NULL DEFAULT '0'," +
						"`written_total` float UNSIGNED NOT NULL DEFAULT '0'," +
						"`exam_total` float UNSIGNED NOT NULL DEFAULT '0'," +
						"`exam_date` datetime NOT NULL," +
						"PRIMARY KEY (`id`)," +
						"KEY `session_id` (`session_id`)," +
						"KEY `class_id` (`class_id`)," +
						"KEY `section_id` (`section_id`)" +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		db.updateQuery(query);
	}

	private void table_fees() throws SQLException, DatabaseException {
		String query = "CREATE TABLE IF NOT EXISTS `"+db.getTable(Configuration.TABLE_FEES)+"` (" +
						"`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT," +
						"`type_id` int(10) UNSIGNED NOT NULL," +
						"`section_id` int(10) UNSIGNED NOT NULL," +
						"`amount` float NOT NULL DEFAULT '0'," +
						"`comment` text COLLATE utf8mb4_unicode_ci NOT NULL," +
						"`created` datetime NOT NULL," +
						"PRIMARY KEY (`id`)," +
						"KEY `type_id` (`type_id`)," +
						"KEY `section_id` (`section_id`)" +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		db.updateQuery(query);
	}

	private void table_fees_history() throws SQLException, DatabaseException {
		String query = "CREATE TABLE IF NOT EXISTS `"+db.getTable(Configuration.TABLE_FEES_HISTORY)+"` (" +
						"`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT," +
						"`fees_id` int(10) UNSIGNED NOT NULL," +
						"`amount` float NOT NULL DEFAULT '0'," +
						"`created` datetime NOT NULL," +
						"PRIMARY KEY (`id`)," +
						"KEY `fees_id` (`fees_id`)" +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		db.updateQuery(query);
	}

	private void table_fees_type() throws SQLException, DatabaseException {
		String query = "CREATE TABLE IF NOT EXISTS `"+db.getTable(Configuration.TABLE_FEES_TYPE)+"` (" +
						"`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT," +
						"`name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL," +
						"PRIMARY KEY (`id`)" +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		db.updateQuery(query);
	}

	private void table_grading() throws SQLException, DatabaseException {
		String query = "CREATE TABLE IF NOT EXISTS `"+db.getTable(Configuration.TABLE_GRADING)+"` (" +
						"`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT," +
						"`grade` varchar(55) COLLATE utf8mb4_unicode_ci NOT NULL," +
						"`gpa` float UNSIGNED NOT NULL DEFAULT '0'," +
						"`marks_form` float UNSIGNED NOT NULL DEFAULT '0'," +
						"`marks_to` float UNSIGNED NOT NULL DEFAULT '0'," +
						"PRIMARY KEY (`id`)" +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		db.updateQuery(query);
	}

	private void table_invoice() throws SQLException, DatabaseException {
		String query = "CREATE TABLE IF NOT EXISTS `"+db.getTable(Configuration.TABLE_INVOICE)+"` (" +
						"`id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT," +
						"`alpha_numeric_id` varchar(155) COLLATE utf8mb4_unicode_ci NOT NULL," +
						"`roll_id` bigint(20) UNSIGNED NOT NULL," +
						"`sub_total` float UNSIGNED NOT NULL," +
						"`discount` float UNSIGNED NOT NULL," +
						"`grand_total` float UNSIGNED NOT NULL," +
						"`paid_amount` float UNSIGNED NOT NULL," +
						"`payment_status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'F/P/D => Full/Partial/Due'," +
						"`created_by` bigint(20) UNSIGNED NOT NULL," +
						"`created_date` datetime NOT NULL," +
						"PRIMARY KEY (`id`)," +
						"KEY `roll_id` (`roll_id`)," +
						"KEY `created_by` (`created_by`)" +				
						") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		db.updateQuery(query);
	}

	private void table_invoice_item() throws SQLException, DatabaseException {
		String query = "CREATE TABLE IF NOT EXISTS `"+db.getTable(Configuration.TABLE_INVOICE_ITEM)+"` (" +
						"`id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT," +
						"`invoice_id` bigint(20) UNSIGNED NOT NULL," +
						"`fees_id` int(10) UNSIGNED NOT NULL," +
						"`waiver_id` bigint(20) UNSIGNED DEFAULT NULL," +
						"`total_amount` float NOT NULL DEFAULT '0'," +
						"`start_date` date NOT NULL," +
						"`end_date` date NOT NULL," +
						"PRIMARY KEY (`id`)," +
						"KEY `invoice_id` (`invoice_id`)," +
						"KEY `fees_id` (`fees_id`)," +
						"KEY `waiver_id` (`waiver_id`)" +		
						") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		db.updateQuery(query);
	}

	private void table_invoice_payment() throws SQLException, DatabaseException {
		String query = "CREATE TABLE IF NOT EXISTS `"+db.getTable(Configuration.TABLE_INVOICE_PAYMENT)+"` (" +
						"`id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT," +
						"`invoice_id` bigint(20) UNSIGNED NOT NULL," +
						"`amount` float NOT NULL DEFAULT '0'," +
						"`pay_method` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'C/B => Cash/bKash'," +
						"`transaction_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL," +
						"`paid_by` bigint(20) UNSIGNED NOT NULL," +
						"`paid_date` datetime NOT NULL," +
						"PRIMARY KEY (`id`)," +
						"KEY `invoice_id` (`invoice_id`)," +
						"KEY `paid_by` (`paid_by`)" +	
						") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		db.updateQuery(query);

		query = "ALTER TABLE `"+db.getTable(Configuration.TABLE_INVOICE_PAYMENT)+"` AUTO_INCREMENT=1371;";
		db.updateQuery(query);
	}

	private void table_marks() throws SQLException, DatabaseException {
		String query = "CREATE TABLE IF NOT EXISTS `"+db.getTable(Configuration.TABLE_MARKS)+"` (" +
						"`id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT," +
						"`exam_id` bigint(20) UNSIGNED NOT NULL," +
						"`roll_id` bigint(20) UNSIGNED NOT NULL," +
						"`mcq_marks` float UNSIGNED NOT NULL DEFAULT '0'," +
						"`written_marks` float UNSIGNED NOT NULL DEFAULT '0'," +
						"`total_marks` float UNSIGNED NOT NULL DEFAULT '0'," +
						"`created` datetime NOT NULL," +
						"PRIMARY KEY (`id`)," +
						"KEY `exam_id` (`exam_id`)," +
						"KEY `roll_id` (`roll_id`)" +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		db.updateQuery(query);
	}

	private void table_salary() throws SQLException, DatabaseException {
		String query = "CREATE TABLE IF NOT EXISTS `"+db.getTable(Configuration.TABLE_SALARY)+"` (" +
						"`id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT," +
						"`user_id` bigint(10) UNSIGNED NOT NULL," +
						"`month` int(10) UNSIGNED NOT NULL," +
						"`year` int(10) UNSIGNED NOT NULL," +
						"`salary` float UNSIGNED NOT NULL," +
						"`bonus` float UNSIGNED NOT NULL," +
						"`dues` float UNSIGNED NOT NULL," +
						"`advance` float UNSIGNED NOT NULL," +
						"`total` float UNSIGNED NOT NULL," +
						"`comment` text COLLATE utf8mb4_unicode_ci NOT NULL," +
						"PRIMARY KEY (`id`)," +
						"KEY `user_id` (`user_id`)" +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		db.updateQuery(query);
	}

	private void table_section() throws SQLException, DatabaseException {
		String query = "CREATE TABLE IF NOT EXISTS `"+db.getTable(Configuration.TABLE_SECTION)+"` (" +
						"`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT," +
						"`class_id` int(10) UNSIGNED NOT NULL," +
						"`name` varchar(155) COLLATE utf8mb4_unicode_ci NOT NULL," +
						"PRIMARY KEY (`id`)," +
						"KEY `class_id` (`class_id`)" +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		db.updateQuery(query);
	}

	private void table_session() throws SQLException, DatabaseException {
		String query = "CREATE TABLE IF NOT EXISTS `"+db.getTable(Configuration.TABLE_SESSION)+"` (" +
						"`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT," +
						"`start` int(10) UNSIGNED NOT NULL," +
						"`end` int(10) UNSIGNED NOT NULL," +
						"`cached` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '0/1 => removed/added'," +
						"`created` datetime NOT NULL," +
						"PRIMARY KEY (`id`)" +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		db.updateQuery(query);
	}

	private void table_session_data() throws SQLException, DatabaseException {
		String query = "CREATE TABLE IF NOT EXISTS `"+db.getTable(Configuration.TABLE_SESSION_DATA)+"` (" +
  						"`roll` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT," +
  						"`session_id` int(10) UNSIGNED NOT NULL," +
 						"`section_id` int(10) UNSIGNED NOT NULL," +
  						"`user_id` bigint(20) UNSIGNED NOT NULL," +
  						"`status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '0/1 => Active/Terminated'," +
  						"`migrated` datetime DEFAULT NULL," +
  						"PRIMARY KEY (`roll`)," +
  						"KEY `session_id` (`session_id`)," +
  						"KEY `user_id` (`user_id`)," +
  						"KEY `section_id` (`section_id`)" +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		db.updateQuery(query);

		query = "ALTER TABLE `"+db.getTable(Configuration.TABLE_SESSION_DATA)+"` AUTO_INCREMENT=100001;";
		db.updateQuery(query);
	}

	private void table_users() throws SQLException, DatabaseException {
		String query = "CREATE TABLE IF NOT EXISTS `"+db.getTable(Configuration.TABLE_USERS)+"` (" +
						"`id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT," +
						"`email` varchar(155) COLLATE utf8mb4_unicode_ci NOT NULL," +
						"`password` varchar(155) COLLATE utf8mb4_unicode_ci NOT NULL," +
						"`role` varchar(55) COLLATE utf8mb4_unicode_ci NOT NULL," +
						"`registered` datetime NOT NULL," +
						"PRIMARY KEY (`id`)" +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		db.updateQuery(query);
	}

	private void table_users_data() throws SQLException, DatabaseException {
		String query = "CREATE TABLE IF NOT EXISTS `"+db.getTable(Configuration.TABLE_USERS_DATA)+"` (" +
						"`id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT," +
						"`user_id` bigint(20) UNSIGNED NOT NULL," +
						"`data_key` varchar(155) COLLATE utf8mb4_unicode_ci NOT NULL," +
						"`data_value` longtext COLLATE utf8mb4_unicode_ci NOT NULL," +
						"PRIMARY KEY (`id`)," +
						"KEY `user_id` (`user_id`)" +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		db.updateQuery(query);
	}

	private void table_waiver() throws SQLException, DatabaseException {
		String query = "CREATE TABLE IF NOT EXISTS `"+db.getTable(Configuration.TABLE_WAIVER)+"` (" +
						"`id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT," +
						"`roll_id` bigint(20) UNSIGNED NOT NULL," +
						"`fees_id` int(10) UNSIGNED NOT NULL," +
						"`name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL," +
						"`amount` float UNSIGNED NOT NULL," +
						"`created` datetime NOT NULL," +
						"PRIMARY KEY (`id`)," +
						"KEY `roll_id` (`roll_id`)," +
						"KEY `fees_id` (`fees_id`)" +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		db.updateQuery(query);
	}

	private void table_relations() throws SQLException, DatabaseException {
		String query;

		query = "ALTER TABLE `"+db.getTable(Configuration.TABLE_ATTENDANCE)+"`" +
				"ADD CONSTRAINT `fk_attendance_users_roll` FOREIGN KEY (`roll_id`) REFERENCES `"+db.getTable(Configuration.TABLE_USERS)+"` (`id`)," +
				"ADD CONSTRAINT `fk_attendance_users_taken` FOREIGN KEY (`taken_by`) REFERENCES `"+db.getTable(Configuration.TABLE_USERS)+"` (`id`);";
		db.updateQuery(query);

		query = "ALTER TABLE `"+db.getTable(Configuration.TABLE_EXAMS)+"`" +
				"ADD CONSTRAINT `fk_exams_class` FOREIGN KEY (`class_id`) REFERENCES `"+db.getTable(Configuration.TABLE_CLASSES)+"` (`id`)," +
				"ADD CONSTRAINT `fk_exams_section` FOREIGN KEY (`section_id`) REFERENCES `"+db.getTable(Configuration.TABLE_SECTION)+"` (`id`)," +
				"ADD CONSTRAINT `fk_exams_session` FOREIGN KEY (`session_id`) REFERENCES `"+db.getTable(Configuration.TABLE_SESSION)+"` (`id`);";
		db.updateQuery(query);

		query = "ALTER TABLE `"+db.getTable(Configuration.TABLE_FEES)+"`" +
				"ADD CONSTRAINT `fk_fees_section` FOREIGN KEY (`section_id`) REFERENCES `"+db.getTable(Configuration.TABLE_SECTION)+"` (`id`)," +
				"ADD CONSTRAINT `fk_fees_type` FOREIGN KEY (`type_id`) REFERENCES `"+db.getTable(Configuration.TABLE_FEES_TYPE)+"` (`id`);";
		db.updateQuery(query);

		query = "ALTER TABLE `"+db.getTable(Configuration.TABLE_FEES_HISTORY)+"`" +
				"ADD CONSTRAINT `fk_fees_history_fees` FOREIGN KEY (`fees_id`) REFERENCES `"+db.getTable(Configuration.TABLE_FEES)+"` (`id`);";
		db.updateQuery(query);

		query = "ALTER TABLE `"+db.getTable(Configuration.TABLE_INVOICE)+"`" +
				"ADD CONSTRAINT `fk_invoice_session_data` FOREIGN KEY (`roll_id`) REFERENCES `"+db.getTable(Configuration.TABLE_SESSION_DATA)+"` (`roll`)," +
				"ADD CONSTRAINT `fk_invoice_users` FOREIGN KEY (`created_by`) REFERENCES `"+db.getTable(Configuration.TABLE_USERS)+"` (`id`);";
		db.updateQuery(query);

		query = "ALTER TABLE `"+db.getTable(Configuration.TABLE_INVOICE_ITEM)+"`" +
				"ADD CONSTRAINT `fk_invoice_item_fees` FOREIGN KEY (`fees_id`) REFERENCES `"+db.getTable(Configuration.TABLE_FEES)+"` (`id`)," +
				"ADD CONSTRAINT `fk_invoice_item_invoice` FOREIGN KEY (`invoice_id`) REFERENCES `"+db.getTable(Configuration.TABLE_INVOICE)+"` (`id`)," +
				"ADD CONSTRAINT `fk_invoice_item_waiver` FOREIGN KEY (`waiver_id`) REFERENCES `"+db.getTable(Configuration.TABLE_WAIVER)+"` (`id`);";
		db.updateQuery(query);

		query = "ALTER TABLE `"+db.getTable(Configuration.TABLE_INVOICE_PAYMENT)+"`" +
				"ADD CONSTRAINT `fk_invoice_payment_invoice` FOREIGN KEY (`invoice_id`) REFERENCES `"+db.getTable(Configuration.TABLE_INVOICE)+"` (`id`)," +
				"ADD CONSTRAINT `fk_invoice_payment_users` FOREIGN KEY (`paid_by`) REFERENCES `"+db.getTable(Configuration.TABLE_USERS)+"` (`id`);";
		db.updateQuery(query);

		query = "ALTER TABLE `"+db.getTable(Configuration.TABLE_MARKS)+"`" +
				"ADD CONSTRAINT `fk_marks_exams` FOREIGN KEY (`exam_id`) REFERENCES `"+db.getTable(Configuration.TABLE_EXAMS)+"` (`id`)," +
				"ADD CONSTRAINT `fk_marks_session_data` FOREIGN KEY (`roll_id`) REFERENCES `"+db.getTable(Configuration.TABLE_SESSION_DATA)+"` (`roll`);";
		db.updateQuery(query);

		query = "ALTER TABLE `"+db.getTable(Configuration.TABLE_SALARY)+"`" +
				"ADD CONSTRAINT `fk_salary_users` FOREIGN KEY (`user_id`) REFERENCES `"+db.getTable(Configuration.TABLE_USERS)+"` (`id`);";
		db.updateQuery(query);

		query = "ALTER TABLE `"+db.getTable(Configuration.TABLE_SECTION)+"`" +
				"ADD CONSTRAINT `fk_classes_section` FOREIGN KEY (`class_id`) REFERENCES `"+db.getTable(Configuration.TABLE_CLASSES)+"` (`id`);";
		db.updateQuery(query);

		query = "ALTER TABLE `"+db.getTable(Configuration.TABLE_SESSION_DATA)+"`" +
  				"ADD CONSTRAINT `fk_session_data_section` FOREIGN KEY (`section_id`) REFERENCES `"+db.getTable(Configuration.TABLE_SECTION)+"` (`id`)," +
 				"ADD CONSTRAINT `fk_session_data_session` FOREIGN KEY (`session_id`) REFERENCES `"+db.getTable(Configuration.TABLE_SESSION)+"` (`id`)," +
  				"ADD CONSTRAINT `fk_session_data_users` FOREIGN KEY (`user_id`) REFERENCES `"+db.getTable(Configuration.TABLE_USERS)+"` (`id`);";
  		db.updateQuery(query);

  		query = "ALTER TABLE `"+db.getTable(Configuration.TABLE_USERS_DATA)+"`" +
				"ADD CONSTRAINT `fk_users_data_users` FOREIGN KEY (`user_id`) REFERENCES `"+db.getTable(Configuration.TABLE_USERS)+"` (`id`);";
		db.updateQuery(query);

		query = "ALTER TABLE `"+db.getTable(Configuration.TABLE_WAIVER)+"`" +
				"ADD CONSTRAINT `fk_waiver_fees` FOREIGN KEY (`fees_id`) REFERENCES `tm_fees` (`id`)," +
				"ADD CONSTRAINT `fk_waiver_session_data` FOREIGN KEY (`roll_id`) REFERENCES `tm_session_data` (`roll`);";
		db.updateQuery(query);
	}

}