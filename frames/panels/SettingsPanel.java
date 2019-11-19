package frames.panels;

import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;

import library.*;
import frames.panels.core.*;

public class SettingsPanel extends TitledPanel {

	private Point coordinates;

	public SettingsPanel(Point coordinates, Dimension dimension) {
		super(dimension);
		this.coordinates = coordinates;
		Construct();
	}

	public SettingsPanel(Point coordinates, Dimension dimension, boolean isDoubleBuffered) {
		super(dimension, isDoubleBuffered);
		this.coordinates = coordinates;
		Construct();
	}

	public SettingsPanel(Point coordinates, Dimension dimension, LayoutManager layout) {
		super(dimension, layout);
		this.coordinates = coordinates;
		Construct();
	}

	public SettingsPanel(Point coordinates, Dimension dimension, LayoutManager layout, boolean isDoubleBuffered) {
		super(dimension, layout, isDoubleBuffered);
		this.coordinates = coordinates;
		Construct();
	}

	private void Construct() {

	
		this.setTitle("Settings");
		this.setBackground(new Color(255,255,255));
		this.setLocation(coordinates);
		this.setSize(dimension);
		disableAdd();
	}
}