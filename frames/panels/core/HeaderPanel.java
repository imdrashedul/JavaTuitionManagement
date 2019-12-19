package frames.panels.core;

import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.event.*;
import java.awt.Cursor;
import java.sql.SQLException;
import library.apachelang3.StringUtils;

import library.*;
import entity.*;
import models.*;

public class HeaderPanel extends JPanel {

	private User user;
	private JLabel userName, userPic;
	private BodyPanel body;
	private JPanel userCard;

	public HeaderPanel(User user) {
		super();
		this.user = user;
		Construct();
	}

	public HeaderPanel(User user, boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		this.user = user;
		Construct();
	}

	public HeaderPanel(User user, LayoutManager layout) {
		super(layout);
		this.user = user;
		Construct();
	}

	public HeaderPanel(User user, LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		this.user = user;
		Construct();
	}

	public void setBody(BodyPanel body) {
		this.body = body;
	}

	public BodyPanel getBody() {
		return this.body;
	}

	private void Construct() {

		SpringLayout sl = new SpringLayout();
		FlowLayout fl = new FlowLayout();

		userCard = new JPanel();
		userCard.setLayout(fl);
		userCard.setBackground(new Color(66, 157, 46));

		String namePreview = "Admin";

		try {
			MUserMeta mUserMeta = new MUserMeta();
			UserMeta metaFirstName = mUserMeta.retrive(user.getId(), Configuration.META_FNAME);
			UserMeta metaLastName = mUserMeta.retrive(user.getId(), Configuration.META_LNAME);

			String firstName = metaFirstName.getValue();
			String lastName = metaLastName.getValue();

			namePreview = !StringUtils.isEmpty(firstName) ? firstName : (!StringUtils.isEmpty(lastName) ? lastName : namePreview);

		} catch(DatabaseException | SQLException ex) {}

		userName = new JLabel(namePreview);
		userName.setFont( Configuration.getFont(Configuration.FONT_REGULAR, Font.BOLD, 14) );
		userName.setForeground(new Color(255, 255, 255));

		userPic = new JLabel("[x]");

		try {
			userPic = new JLabel(Helper.circularImage( Helper.bufferedImage(Helper.getAvatar(user)), 40 ));
		} catch(DatabaseException | SQLException | IOException ex){}

		userCard.add(userPic);
		userCard.add(userName);
		userCard.setCursor(new Cursor(Cursor.HAND_CURSOR));
		userCard.addMouseListener(listenUserCard(userCard));
		this.add(userCard);


		JLabel lbLogo = new JLabel(new ImageIcon(Configuration.PATH_IMAGE + "logo.png"));
		this.add(lbLogo);

		this.setLayout(sl);
		this.setBounds(0, 0, Configuration.frameWidth(), Configuration.HEADER_HEIGHT);
		this.setBackground(new Color(66, 157, 46));

		sl.putConstraint(SpringLayout.WEST, lbLogo, 17, SpringLayout.WEST, this);
		sl.putConstraint(SpringLayout.NORTH, lbLogo, 9, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.EAST, userCard, -17, SpringLayout.EAST, this);
		sl.putConstraint(SpringLayout.NORTH, userCard, 5, SpringLayout.NORTH, this);

	}

	public void setUserName(String name) {
		userName.setText(name);
		userCard.revalidate();
		userCard.repaint();
	}

	public void setUserAvatar(File image) {
		try {
			userPic.setIcon(Helper.circularImage( Helper.bufferedImage(image), 40 ));
		} catch(IOException ex){}
	}

	private MouseListener listenUserCard(JPanel panel) {
		Color in = new Color(60,142,41);
		Color out = new Color(66,157,46);
		return new MouseAdapter() {
			public void mouseClicked(MouseEvent me){
				body.aclInvoke("Settings");
			}
			public void mousePressed(MouseEvent me){
				panel.setBackground(in);
			}
			public void mouseReleased(MouseEvent me){
				panel.setBackground(out);
			}
			public void mouseEntered(MouseEvent me){
				panel.setBackground(in);
			}
			public void mouseExited(MouseEvent me){
				panel.setBackground(out);
			}
		};
	}
}