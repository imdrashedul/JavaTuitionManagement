package frames.panels.core;

import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.AlphaComposite;
import java.awt.RenderingHints;
import java.io.IOException;
import java.awt.Image;
import java.awt.event.*;
import java.awt.Cursor;

import library.*;

public class HeaderPanel extends JPanel {

	public HeaderPanel() {
		super();
		Construct();
	}

	public HeaderPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		Construct();
	}

	public HeaderPanel(LayoutManager layout) {
		super(layout);
		Construct();
	}

	public HeaderPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		Construct();
	}

	private void Construct() {

		SpringLayout sl = new SpringLayout();
		FlowLayout fl = new FlowLayout();

		JPanel userCard = new JPanel();
		userCard.setLayout(fl);
		userCard.setBackground(new Color(0, 0, 0, 0));

		JLabel userName = new JLabel("Rashedul");
		userName.setFont( Configuration.getFont(Configuration.FONT_REGULAR, Font.BOLD, 14) );
		userName.setForeground(new Color(255, 255, 255));

		JLabel userPic = new JLabel("[x]");

		try {
	    	File file = new File(Configuration.PATH_IMAGE + "userpic.jpg");
			BufferedImage image = ImageIO.read(file);
			userPic = null;
			userPic = new JLabel(Circular(image, 40));
		} catch(IOException e){
		    e.printStackTrace();
		}

		userCard.add(userPic);
		userCard.add(userName);
		userCard.setCursor(new Cursor(Cursor.HAND_CURSOR));
		userCard.addMouseListener(listenUserCard(userCard));
		this.add(userCard);


		JLabel lbLogo = new JLabel(new ImageIcon(Configuration.PATH_IMAGE + "logo1.png"));
		this.add(lbLogo);

		this.setLayout(sl);
		this.setBounds(0, 0, Configuration.frameWidth(), Configuration.HEADER_HEIGHT);
		this.setBackground(new Color(66, 157, 46));

		sl.putConstraint(SpringLayout.WEST, lbLogo, 17, SpringLayout.WEST, this);
		sl.putConstraint(SpringLayout.NORTH, lbLogo, 9, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.EAST, userCard, -17, SpringLayout.EAST, this);
		sl.putConstraint(SpringLayout.NORTH, userCard, 5, SpringLayout.NORTH, this);

	}

	private ImageIcon Circular(BufferedImage master, int size) {
		int newW = size;
		int newH = size;

		Image temp = master.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		int diameter = Math.min(newW, newH);
    	BufferedImage mask = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = mask.createGraphics();
	    applyQualityRenderingHints(g2d);
	    g2d.fillOval(0, 0, diameter - 1, diameter - 1);
	    g2d.dispose();

	    BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
	    g2d = masked.createGraphics();
	    applyQualityRenderingHints(g2d);
	    int x = (diameter - newW) / 2;
	    int y = (diameter - newH) / 2;
	    g2d.drawImage(temp, x, y, null);
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
	    g2d.drawImage(mask, 0, 0, null);
	    g2d.dispose();

	    return new ImageIcon(masked);
	}

	private void applyQualityRenderingHints(Graphics2D g2d) {

	    g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

	}


	private MouseListener listenUserCard(JPanel panel) {
		Color in = new Color(60,142,41);
		Color out = new Color(66,157,46);
		return new MouseAdapter() {
			public void mouseClicked(MouseEvent me){
				System.out.println("clicked");
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