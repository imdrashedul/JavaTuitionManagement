package library;

import java.lang.*;
import javax.swing.*;
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class Configuration {

	public static final String PATH_IMAGE = "assets/images/";
	public static final ImageIcon SYSTEM_ICON = new ImageIcon("assets/images/appicon.png");
	public static final ImageIcon MENU_ICON = new ImageIcon("assets/images/it.png");
	
	public static final String DB_NAME = "java_tuition_management";
	public static final String DB_FILE = ".env";
	public static final String DB_USER = "root";
	public static final String DB_PASS = "";
	public static final String DB_PREFIX = "tm_";

	public static final String TABLE_ATTENDANCE = "attendance";
	public static final String TABLE_CLASSES = "classes";
	public static final String TABLE_EXAMS = "exams";
	public static final String TABLE_FEES = "fees";
	public static final String TABLE_FEES_HISTORY = "fees_history";
	public static final String TABLE_FEES_TYPE = "fees_type";
	public static final String TABLE_GRADING = "grading";
	public static final String TABLE_INVOICE = "invoice";
	public static final String TABLE_INVOICE_ITEM = "invoice_item";
	public static final String TABLE_INVOICE_PAYMENT = "invoice_payment";
	public static final String TABLE_MARKS = "marks";
	public static final String TABLE_SALARY = "salary";
	public static final String TABLE_SECTION = "section";
	public static final String TABLE_SESSION = "session";
	public static final String TABLE_SESSION_DATA = "session_data";
	public static final String TABLE_USERS = "users";
	public static final String TABLE_USERS_DATA = "users_data";
	public static final String TABLE_WAIVER = "waiver";

	public static final String FONT_PRIMARY = "TimesRoman";
	public static final String FONT_LIGHT = "MontserratLight.ttf";
	public static final String FONT_REGULAR = "MontserratRegular.ttf";
	public static final String SYSTEM_DEVELOPER = "M. Rashedul Islam";
	public static final String ENC_SALT = "$a1h9Sa&6pLxS1";
	
	public static final String ROLE_ADMIN = "admin";
	public static final String ROLE_EMPLOYEE = "employee";
	public static final String ROLE_STUDENT = "student";

	public static final int SIDEBAR_WIDTH = 260;
	public static final int HEADER_HEIGHT = 60;
	public static final int FOOTER_HEIGHT = 60;
	private static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	public static String copyright()
	{
		LocalDateTime dtLocal = LocalDateTime.now();
	    DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy");
	    String dtFormatted = dtLocal.format(dtFormatter);
	    return "\u00a9 "+( dtFormatted.equals("2019") ? "2019" : "2019-"+dtFormatted );
	}

	public static int mainWidth() {
		return dim.width+6;
	}

	public static int mainHeight() {
		return dim.height-36;
	}

	public static int frameWidth() {
		return mainWidth()-6;
	}

	public static int frameHeight() {
		return mainHeight()-29;
	}

	private static Font getFont(String name) {

		Font font;

		String file = "assets/fonts/" + name;

		try {
			InputStream stream = new BufferedInputStream( new FileInputStream(file) );
            font = Font.createFont(Font.TRUETYPE_FONT, stream);
		} catch(Exception ex) {
			ex.printStackTrace();
      		System.err.println(file + " not loaded.  Using serif font.");
			font = new Font(Font.SANS_SERIF, Font.PLAIN, 25);
		}
		
		return font;
	}

	public static void setDatePickerIcon(JButton dpButton) {
		dpButton.setText("");
        dpButton.setIcon(new ImageIcon(PATH_IMAGE + "pickdate.png"));
	}

	public static Font getFont(String name, int style, int size) {
		Font f = getFont(name);
        return f.deriveFont(style, size);
	}

}
