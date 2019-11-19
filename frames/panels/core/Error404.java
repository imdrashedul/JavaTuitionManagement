package frames.panels.core;

import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Component;

import library.*;

public class Error404 extends JPanel {

	private Point coordinates;
	private Dimension dimension;

	public Error404(Point coordinates, Dimension dimension) {
		super();
		this.coordinates = coordinates;
		this.dimension = dimension;
		Construct();
	}

	public Error404(Point coordinates, Dimension dimension, boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		Construct();
	}

	public Error404(LayoutManager layout) {
		super(layout);
		this.coordinates = coordinates;
		this.dimension = dimension;
		Construct();
	}

	public Error404(Point coordinates, Dimension dimension, LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		this.coordinates = coordinates;
		this.dimension = dimension;
		Construct();
	}

	private void Construct() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		double h = (dimension.getHeight()/2) - 40;

		Dimension d = new Dimension();
		d.setSize(dimension.getWidth(), dimension.getHeight()-h);
		Point p = new Point();
		p.setLocation(coordinates.getX(), coordinates.getY()+h);

		JLabel label404 = new JLabel("404");
		label404.setFont( Configuration.getFont(Configuration.FONT_REGULAR, Font.BOLD, 62) );
		label404.setForeground(new Color(121, 121, 121));
		label404.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel labelDescription = new JLabel("No Instance Found !");
		labelDescription.setFont( Configuration.getFont(Configuration.FONT_REGULAR, Font.BOLD, 20) );
		labelDescription.setForeground(new Color(121, 121, 121));
		labelDescription.setAlignmentX(Component.CENTER_ALIGNMENT); 
		this.add(label404);
		this.add(labelDescription);
		this.setLocation(p);
		this.setSize(d);
	}
}