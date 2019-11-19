package frames.panels.core;

import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;

import library.*;

public class FooterPanel extends JPanel {

	public FooterPanel() {
		super();
		Construct();
	}

	public FooterPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		Construct();
	}

	public FooterPanel(LayoutManager layout) {
		super(layout);
		Construct();
	}

	public FooterPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		Construct();
	}

	private void Construct() {

		SpringLayout sl = new SpringLayout();

		this.setLayout(sl);
		this.setBounds(Configuration.SIDEBAR_WIDTH, Configuration.frameHeight() - Configuration.FOOTER_HEIGHT, Configuration.frameWidth()-Configuration.SIDEBAR_WIDTH, Configuration.FOOTER_HEIGHT);
		this.setBackground(new Color(216,216,217));

		JLabel copyRight = new JLabel(Configuration.copyright());
		copyRight.setFont( Configuration.getFont(Configuration.FONT_REGULAR, Font.BOLD, 14) );
		this.add(copyRight);

		JLabel creditLabel = new JLabel("Software By "+Configuration.SYSTEM_DEVELOPER, SwingConstants.RIGHT);
		creditLabel.setFont(Configuration.getFont(Configuration.FONT_REGULAR, Font.BOLD, 14));
		this.add(creditLabel);

		sl.putConstraint(SpringLayout.WEST, copyRight, 15, SpringLayout.WEST, this);
		sl.putConstraint(SpringLayout.EAST, creditLabel, -15, SpringLayout.EAST, this);
		sl.putConstraint(SpringLayout.NORTH, copyRight, (Configuration.FOOTER_HEIGHT/2)-5, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.NORTH, creditLabel, (Configuration.FOOTER_HEIGHT/2)-5, SpringLayout.NORTH, this);
	}
}