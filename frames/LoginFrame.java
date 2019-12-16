package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;

// Custom package
import library.*;
import models.*;
import entity.*;
import library.apachelang3.StringUtils;

public class LoginFrame extends JFrame implements ActionListener
{
	private JLabel loginTitle, loginTitleImage, labelEmail, labelPassword, resetLabel, copyRight, creditLabel;
	private JTextField inputEmail;
	private JPasswordField inputPassword;
	private JButton loginButton;
	private JPanel panel;
	private Dimension dim;

	public LoginFrame()
	{
		super("Tution Management System");
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setIconImage(Configuration.SYSTEM_ICON.getImage());
		this.setSize(400, 500);
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBackground(new Color(233, 235, 238));
		panel = new JPanel();
		panel.setLayout(null);

		loginTitleImage = new JLabel(new ImageIcon(Configuration.PATH_IMAGE+"login_icon_large.png"));
		loginTitleImage.setBounds(160, 50, 52, 53);
		panel.add(loginTitleImage);

		loginTitle = new JLabel("SYSTEM LOGIN");
		loginTitle.setBounds(95, 110, 200, 50);
		loginTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
		panel.add(loginTitle);

		labelEmail = new JLabel("EMAIL:");
		labelEmail.setBounds(90, 175, 200, 10);
		panel.add(labelEmail);

		inputEmail = new JTextField();
		inputEmail.setBounds(90, 190, 200, 30);
		panel.add(inputEmail);

		labelPassword =  new JLabel("PASSWORD:");
		labelPassword.setBounds(90, 230, 200, 10);
		panel.add(labelPassword);

		inputPassword = new JPasswordField();
		inputPassword.setBounds(90, 245, 200, 30);
		panel.add(inputPassword);

		loginButton = new JButton("LOGIN");
		loginButton.setBounds(90, 285, 70, 30);
		loginButton.addActionListener(this);
		panel.add(loginButton);

		resetLabel = new JLabel("Forgot Password ?");
		resetLabel.setBounds(180, 285, 130, 30);
		panel.add(resetLabel);

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
		
		if(command.equals(loginButton.getText()))
		{
			String email = inputEmail.getText();
			String password = inputPassword.getText();

			if(!StringUtils.isEmpty(email) && !StringUtils.isEmpty(password)) {
				try {
					MUser mUser = new MUser();
					User user = mUser.retrive(new Email(email));

					if(user!=null && user.getEmail().get().equals(email)) {
						if(user.getPassHash().equals(HashManager.md5(password))) {
							HomeFrame homeFrame = new HomeFrame(user);
							homeFrame.setVisible(true);
							this.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(this, "Password doesn't match !", "ERROR OCCURED", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(this, "Email not found !", "ERROR OCCURED", JOptionPane.ERROR_MESSAGE);
					}

				} catch (DatabaseException | SQLException ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR OCCURED", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Email and Password are Required", "ERROR OCCURED", JOptionPane.ERROR_MESSAGE);
			}		
		}
		else{}
	}
}