package library;

import javax.swing.*;
import java.io.*;
import java.awt.Image;
import java.sql.SQLException;
import library.apachelang3.StringUtils;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.AlphaComposite;
import java.awt.RenderingHints;

import entity.*;
import models.*;


public class Helper {


	public static File getAvatar(User user) throws DatabaseException, SQLException {

		File file = null;

		String profilePic = "";

		MUserMeta mUserMeta = new MUserMeta();

		UserMeta userProfilePic = mUserMeta.retrive(user.getId(), Configuration.META_PROFILE_PIC);

		if(userProfilePic!=null) {
			profilePic = userProfilePic.getValue();
		}

		if(!StringUtils.isEmpty(profilePic)) {
    		file = new File(Configuration.PATH_UPLOAD + profilePic);
    	 	if(!file.exists()) {
    	 		file = null;
    	 	}
    	} 

    	if(file==null) file = new File(Configuration.PATH_AVATAR);

    	return file;
	}

	public static BufferedImage bufferedImage(File image) throws IOException {
		return ImageIO.read(image);
	}

	public static ImageIcon squareImage(BufferedImage master, int size) {
		return new ImageIcon(master.getScaledInstance(size, size, Image.SCALE_SMOOTH));
	}


	public static ImageIcon circularImage(BufferedImage master, int size) {
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

	private static void applyQualityRenderingHints(Graphics2D g2d) {

	    g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

	}

}