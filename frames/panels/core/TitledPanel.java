package frames.panels.core;

import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Cursor;
import java.awt.event.*;

import library.*;
import frames.panels.*;

public class TitledPanel extends JPanel {

	protected Dimension dimension;
	protected Point locBody;
	protected Dimension sizeBody; 
	private JLabel title, btnAdd;


	public TitledPanel(Dimension dimension) {
		super();
		this.dimension = dimension;
		Construct();
	}

	public TitledPanel(Dimension dimension, boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		this.dimension = dimension;
		Construct();
	}

	public TitledPanel(Dimension dimension, LayoutManager layout) {
		super(layout);
		this.dimension = dimension;
		Construct();
	}

	public TitledPanel(Dimension dimension, LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		this.dimension = dimension;
		Construct();
	}

	private void Construct() {

		SpringLayout sl = new SpringLayout();
		JPanel titleBar = new JPanel();
		titleBar.setLayout(sl);

		titleBar.setBounds(0, 0, (int) dimension.getWidth(), 40);
		titleBar.setBackground(new Color(199,203,209));

		title = new JLabel("Title Goes Here");
		title.setFont( Configuration.getFont(Configuration.FONT_REGULAR, Font.BOLD, 14) );
		titleBar.add(title);

		btnAdd = new JLabel(new ImageIcon( Configuration.PATH_IMAGE + "add_new_button.png" ));
		btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAdd.addMouseListener(listenMouse());
		titleBar.add(btnAdd);
	

		sl.putConstraint(SpringLayout.WEST, title, 15, SpringLayout.WEST, titleBar);
		sl.putConstraint(SpringLayout.EAST, btnAdd, -15, SpringLayout.EAST, titleBar);
		sl.putConstraint(SpringLayout.NORTH, title, 12, SpringLayout.NORTH, titleBar);
		sl.putConstraint(SpringLayout.NORTH, btnAdd, 11, SpringLayout.NORTH, titleBar);

		sizeBody = new Dimension();
		sizeBody.setSize(dimension.getWidth()-10, dimension.getHeight()-40-10);

		locBody = new Point();
		locBody.setLocation(5, 45);
		this.add(titleBar);
		this.setLayout(null);
	}

	protected MouseListener listenMouse() {
		return new MouseAdapter() {
			public void mouseClicked(MouseEvent me){
				
			}
			public void mousePressed(MouseEvent me){
				
			}
			public void mouseReleased(MouseEvent me){
				
			}
			public void mouseEntered(MouseEvent me){
				
			}
			public void mouseExited(MouseEvent me){
				
			}
		};
	}

	protected void disableAdd() {
		btnAdd.setVisible(false);
	}

	protected void setTitle(String title) {
		this.title.setText(title);
	}
}