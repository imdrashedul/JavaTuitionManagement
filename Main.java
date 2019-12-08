
import javax.swing.*;
import java.io.*;
import frames.*;
import library.*;

public class Main 
{
	public static void main(String[] args)
	{
		
		Database db = new Database();

		if(!envExists()) {
			DBInstallerFrame dbInstaller = new DBInstallerFrame();
			dbInstaller.setVisible(true);
			JOptionPane.showMessageDialog(dbInstaller, "System requires a mysql database host\nto store all kind of information.\n\nPlease provide relevant credentials\nto connect the database.", "WARNING", JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				db.connect();
				if(db.isConnected()) {
					LoginFrame loginFrame = new LoginFrame();
					loginFrame.setVisible(true);
				}
			} catch (DatabaseException ex) {
				JOptionPane.showMessageDialog(null, "Database not connected !\n\nPlease remove .env file\nand restart the application\nto reconfiguire or try to fix the\ndb host.\nMy be it's not running\nproperly", "WARNING", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private static boolean envExists() {
		File file = new File(Configuration.DB_FILE);
		return file.exists();
	}
}