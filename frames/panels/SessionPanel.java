package frames.panels;

import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.*;

import library.*;
import frames.panels.core.*;

public class SessionPanel extends TitledPanel {

	private Point coordinates;
	private BodyPanel bp;

	public SessionPanel(BodyPanel bp, Point coordinates, Dimension dimension) {
		super(dimension);
		this.coordinates = coordinates;
		this.bp = bp;
		Construct();
	}

	public SessionPanel(BodyPanel bp, Point coordinates, Dimension dimension, boolean isDoubleBuffered) {
		super(dimension, isDoubleBuffered);
		this.coordinates = coordinates;
		this.bp = bp;
		Construct();
	}

	public SessionPanel(BodyPanel bp, Point coordinates, Dimension dimension, LayoutManager layout) {
		super(dimension, layout);
		this.coordinates = coordinates;
		this.bp = bp;
		Construct();
	}

	public SessionPanel(BodyPanel bp, Point coordinates, Dimension dimension, LayoutManager layout, boolean isDoubleBuffered) {
		super(dimension, layout, isDoubleBuffered);
		this.coordinates = coordinates;
		this.bp = bp;
		Construct();
	}

	private void Construct() {

	
		this.setTitle("Session");
		this.setBackground(new Color(255,255,255));
		this.setLocation(coordinates);
		this.setSize(dimension);
	}

	protected MouseListener listenMouse() {
		return new MouseAdapter() {
			public void mouseClicked(MouseEvent me){
				bp.aclInvoke("AddNewSession");
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
}