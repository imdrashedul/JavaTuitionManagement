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
import java.util.*;
import java.time.*;
import java.time.format.*;

import entity.*;
import models.*;


public class Helper {

	public static  String genDefSessionTitle() {
		String title = "Sessions: ";
		String sessionTitle = "";
		try {
			MSession mSession = new MSession();
			ArrayList<Session> sessions = mSession.table("1");

			int size = sessions.size();
			int i = 1;
			for (Session session:sessions ) {
				sessionTitle += session.getStart() + "-" + session.getEnd() + (size!=i?", ":" ");
				i++;
			}

		} catch(Exception ex) {}

		title += StringUtils.isEmpty(sessionTitle) ? "Click here to set default" : sessionTitle; 

		return title;
	}

	public static boolean emailAssigned(String email) {
		boolean exists = false;
		try {
			MUser mUser = new MUser();
			User user = mUser.retrive(new Email(email));
			exists = user!=null;
		} catch(Exception ex) {} 
		return exists;
	}


	public static LocalDateTime strToDate(String date, String pattern) {
		//"yyyy-MM-dd HH:mm:ss"
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		return dateTime;
	}

	public static String cleanPhoneNo(String no) {
		no = no.replaceAll("\\s","");
        no = no.replaceAll("-","");
        no = no.replaceAll("\\+","");
        no = no.length()>11 ? no.substring(2, no.length()) : no;

        return no;
	}

	public static boolean verifyBdOperator(String number) {
		String[] operators = new String[]{"013", "015", "016", "017", "018", "019"};
        List<String> list = Arrays.asList(operators);
       	return list.contains(number.substring(0, 3));
	}

	public static boolean verifyPhoneNo(String number) {
		number = cleanPhoneNo(number);
		return number.length()==11 && verifyBdOperator(number);
	}

	public static <T> Object getSelectedValue(JComboBox<JcbItem<T>> comboBox) {
		if(comboBox.getSelectedItem()!=null) {
			JcbItem<T> item = (JcbItem<T>) comboBox.getSelectedItem();
			return item.getKey();
		}

		return null;
	}

	public static String getSelectedValue(ButtonGroup group) {
		ButtonModel model = group.getSelection();
		
		if(model!=null) {
			return model.getActionCommand();
		}

		return "";
	}

	public static int generateRandom(int length) {
		Random r = new Random();
		int m = (int) Math.pow(10, length - 1);
    	return m + r.nextInt(9 * m);
	}

	public static <T, E> void initializeCombo(JComboBox<JcbItem<E>> comboBox, HashMap<T, JcbItem<E>> items, T selected, String title) {
		
		if(title != null && !title.equals("")) {
			comboBox.addItem(new JcbItem<E>(title, null));
		}

		if( items!= null) {
			for(Map.Entry<T, JcbItem<E>> item:items.entrySet()) {
				comboBox.addItem(item.getValue());
			}
		}
		
		if(selected!= null && items.containsKey(selected)) {
			comboBox.setSelectedItem(items.get(selected));
		}
	}

	public static String getSessionStr(Session session) {
		return session.getStart() + "-" + session.getEnd();
	}

	public static boolean emailExists(String email) {
		boolean exists = false;
		try {
			MUser mUser = new MUser();
			User user = mUser.retrive(new Email(email));
			exists = (user!=null);
		} catch(Exception ex) {};
		return exists;
	}

	public static boolean isValidEmail(String email) {
	   String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	   return email.matches(regex);
	}

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