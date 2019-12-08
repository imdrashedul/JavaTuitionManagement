package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.StringWriter;
import java.time.*;
import java.sql.SQLException;

// Custom package
import library.apachelang3.StringUtils;
import library.json.*;
import library.*;
import models.*;
import entity.*;

public class DBInstallerFrame extends JFrame implements ActionListener
{
	private JLabel loginTitle, loginTitleImage, labelDbHost, labelDbUsername, labelDbPassword, labelDbPort, labelDbName, copyRight, creditLabel, loadingLabel;
	private JTextField inputDbUsername, inputDbHost, inputDbPort, inputDbName;
	private JPasswordField inputDbPassword;
	private JButton installButton;
	private JPanel panel;
	private Dimension dim;

	public DBInstallerFrame()
	{
		super("Database Installer");
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setIconImage(Configuration.SYSTEM_ICON.getImage());
		this.setSize(400, 500);
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBackground(new Color(233, 235, 238));
		panel = new JPanel();
		panel.setLayout(null);

		loginTitleImage = new JLabel(new ImageIcon(Configuration.PATH_IMAGE+"database.png"));
		loginTitleImage.setBounds(150, 20, 64, 64);
		panel.add(loginTitleImage);

		loginTitle = new JLabel("DB INSTALLER");
		loginTitle.setBounds(100, 70, 200, 50);
		loginTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
		panel.add(loginTitle);

		labelDbName = new JLabel("DATABASE NAME*:");
		labelDbName.setBounds(90, 130, 200, 10);
		panel.add(labelDbName);

		inputDbName = new JTextField("java_tuition_management");
		inputDbName.setBounds(90, 145, 200, 30);
		panel.add(inputDbName);

		labelDbUsername = new JLabel("DB USER NAME*:");
		labelDbUsername.setBounds(90, 180, 200, 10);
		panel.add(labelDbUsername);

		inputDbUsername = new JTextField("root");
		inputDbUsername.setBounds(90, 195, 200, 30);
		panel.add(inputDbUsername);

		labelDbPassword =  new JLabel("DB USER PASSWORD:");
		labelDbPassword.setBounds(90, 230, 200, 10);
		panel.add(labelDbPassword);

		inputDbPassword = new JPasswordField();
		inputDbPassword.setBounds(90, 245, 200, 30);
		panel.add(inputDbPassword);

		labelDbHost = new JLabel("DATABASE HOST*:");
		labelDbHost.setBounds(90, 280, 200, 10);
		panel.add(labelDbHost);

		inputDbHost = new JTextField("localhost");
		inputDbHost.setBounds(90, 295, 200, 30);
		panel.add(inputDbHost);

		labelDbPort = new JLabel("DATABASE PORT*:");
		labelDbPort.setBounds(90, 330, 200, 10);
		panel.add(labelDbPort);

		inputDbPort = new JTextField("3306");
		inputDbPort.setBounds(90, 345, 200, 30);
		panel.add(inputDbPort);

		installButton = new JButton("INSTALL");
		installButton.setBounds(90, 380, 100, 30);
		installButton.addActionListener(this);
		panel.add(installButton);

		loadingLabel = new JLabel("PLEASE WAIT..");
		loadingLabel.setBounds(100, 200, 200, 50);
		loadingLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
		loadingLabel.setVisible(false);
		panel.add(loadingLabel);

		copyRight = new JLabel(Configuration.copyright());
		copyRight.setBounds(10, 450, 100, 15);
		panel.add(copyRight);

		creditLabel = new JLabel("Software By "+Configuration.SYSTEM_DEVELOPER, SwingConstants.RIGHT);
		creditLabel.setBounds(110, 450, 275, 15);
		panel.add(creditLabel);

		this.add(panel);
	}

	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(installButton.getText()))
		{

			String dbName = inputDbName.getText();
			String dbUser = inputDbUsername.getText();
			String dbPass = inputDbPassword.getText();
			String dbHost = inputDbHost.getText();
			String dbPort = inputDbPort.getText();

			installButton.setEnabled(false);
			inputDbName.setEnabled(false);
			inputDbUsername.setEnabled(false);
			inputDbPassword.setEnabled(false);
			inputDbHost.setEnabled(false);
			inputDbPort.setEnabled(false);

			if(!StringUtils.isEmpty(dbName) && !StringUtils.isEmpty(dbUser) && !StringUtils.isEmpty(dbHost) && !StringUtils.isEmpty(dbPort)) {
				Database db = new Database(dbName, dbUser, dbPass, Configuration.DB_PREFIX, dbHost, dbPort);
				try {
					db.connect();
					if(db.isConnected()) {
						db.disconnect();
						JSONObject obj = new JSONObject();

						obj.put("db_name", dbName);
						obj.put("db_host", dbHost);
						obj.put("db_port", dbPort);
						obj.put("db_user", dbUser);
						obj.put("db_pass", dbPass);

						StringWriter out = new StringWriter();
					    obj.writeJSONString(out);
					      
					    String jsonText = out.toString();
						
						FileManager fileManager = new FileManager(Configuration.DB_FILE);
						fileManager.write(EncManager.encrypt(jsonText, Configuration.ENC_SALT), false);

						int input = JOptionPane.showConfirmDialog(this, "Do you want to Import Database?", "Choose Wisely", JOptionPane.OK_CANCEL_OPTION);

						if(input==0) {

							try {
								DBImporter dbImporter = new DBImporter();
								dbImporter.importDb();

								MUser mUser = new MUser();
								User user = new User(
									null,
									new Email("admin@system.java"),
									HashManager.md5("admin"),
									Configuration.ROLE_ADMIN,
									LocalDateTime.now()
								);
								mUser.insert(user);

								JOptionPane.showMessageDialog(this,"Database Imported Succesfully\nDefault Credentials,\nEmail: admin@system.java\nPassword: admin", "Installation Succesfull", JOptionPane.INFORMATION_MESSAGE);  
							} catch (SQLException ex) {
								JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR OCCURED", JOptionPane.ERROR_MESSAGE);
							}

						} else {
							JOptionPane.showMessageDialog(this,"Database Configuration Updated Succesfully", "Installation Succesfull", JOptionPane.INFORMATION_MESSAGE);
						}
						
						LoginFrame loginFrame = new LoginFrame();
						loginFrame.setVisible(true);
						this.setVisible(false);

					} else {
						JOptionPane.showMessageDialog(this, "Connection not Established !", "ERROR OCCURED", JOptionPane.ERROR_MESSAGE);
					}
				} catch(DatabaseException | IOException ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR OCCURED", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Required Fields are missing.\n* Marked Fields are required", "ERROR OCCURED", JOptionPane.ERROR_MESSAGE);
			}

			installButton.setEnabled(true);
			inputDbName.setEnabled(true);
			inputDbUsername.setEnabled(true);
			inputDbPassword.setEnabled(true);
			inputDbHost.setEnabled(true);
			inputDbPort.setEnabled(true);
		}
	}
}