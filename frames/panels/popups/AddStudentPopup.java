package frames.panels.popups;

import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.*;

import library.*;
import frames.panels.core.*;

public class AddStudentPopup extends JDialog implements ActionListener {
	public AddStudentPopup(JFrame jf) {
		super(jf, "Add New Student", true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(450, 500, 100, 30);
		btnAdd.addActionListener(this);

		this.add(btnAdd);
		setExtendedSize(new Dimension(600,600));    
        this.setVisible(true);  
	}

	private void setExtendedSize(Dimension dimension)
	{
		Point location = new Point();
		location.setLocation((Configuration.mainWidth()/2) - (dimension.getWidth()/2), (Configuration.mainHeight()/2) - (dimension.getHeight()/2));
		this.setSize(dimension);
		this.setLocation(location);
	}

	public void actionPerformed(ActionEvent e) {
	    dispose();
	}
}