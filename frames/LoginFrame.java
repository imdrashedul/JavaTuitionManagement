package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

// Custom package
import library.*;

public class LoginFrame extends JFrame implements ActionListener
{
	private JLabel loginTitle, loginTitleImage, labelUsername, labelPassword, resetLabel, copyRight, creditLabel;
	private JTextField inputUsername;
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

		labelUsername = new JLabel("USERNAME:");
		labelUsername.setBounds(90, 175, 200, 10);
		panel.add(labelUsername);

		inputUsername = new JTextField();
		inputUsername.setBounds(90, 190, 200, 30);
		panel.add(inputUsername);

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
			//UserRepo ur = new UserRepo();
			//User user = ur.getUser(userTF.getText(), passPF.getText());		
			
			// if(user != null)
			// {
			// 	if(user.getStatus() == 0 || user.getStatus() == 1)
			// 	{
					HomeFrame homeFrame = new HomeFrame();
					//adminHome.setExtendedState(JFrame.MAXIMIZED_BOTH);
					homeFrame.setVisible(true);
					this.setVisible(false);
			// 	}
			// 	else if(user.getStatus() == 2)
			// 	{
			// 		CustomerHome ch = new CustomerHome(user);
			// 		ch.setVisible(true);
			// 		this.setVisible(false);
			// 	}
			// 	else{}
			// }
			// else
			// {
			// 	JOptionPane.showMessageDialog(this, "Invaild Id or Password");
			// }
		}
		// else if(command.equals(exitBtn.getText()))
		// {
		// 	System.exit(0);
		// }
		// else if(command.equals(regBtn.getText()))
		// {
		// 	RegistrationFrame rf = new RegistrationFrame(this);
		// 	rf.setVisible(true);
		// 	this.setVisible(false);
		// }
		else{}
	}
}