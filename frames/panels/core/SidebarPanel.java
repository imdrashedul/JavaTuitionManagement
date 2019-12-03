package frames.panels.core;

import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Dimension;

import library.*;

public class SidebarPanel extends JPanel {

	private List<String> menuList;
	private HashMap<String, String> menuAction;
	private HashMap<String, JButton> menuButtons;
	private HashMap<String, Color> menuColors;
	private Color baseColor, hoverColor, selectedColor;
	private BodyPanel body;

	public SidebarPanel(BodyPanel body) {
		super();
		this.body = body;
		Construct();
	}

	public SidebarPanel(BodyPanel body, boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		this.body = body;
		Construct();
	}

	public SidebarPanel(BodyPanel body, LayoutManager layout) {
		super(layout);
		this.body = body;
		Construct();
	}

	public SidebarPanel(BodyPanel body, LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		this.body = body;
		Construct();
	}

	private void Construct() {

		int sidebarHight = Configuration.frameHeight()-Configuration.HEADER_HEIGHT;

		baseColor = new Color(47,50,61);
		hoverColor = new Color(38, 40, 49);
		selectedColor = new Color(30, 31, 39);

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBounds(0, Configuration.HEADER_HEIGHT, Configuration.SIDEBAR_WIDTH, sidebarHight);
		this.setBackground(baseColor);

		menuList = new ArrayList<>();
		menuList.add("Dashboard");
		menuList.add("Student");
		menuList.add("Attendance");
		menuList.add("Marks");
		menuList.add("Exam");
		menuList.add("Invoice");
		menuList.add("Fees");
		menuList.add("Session");
		menuList.add("Class");
		menuList.add("Section");
		menuList.add("Grading");
		menuList.add("Employee");
		menuList.add("Salary");
		menuList.add("Settings");

		menuAction = new HashMap<>();
		menuAction.put("Dashboard", "DashBoard");
		menuAction.put("Student", "Students");
		menuAction.put("Attendance", "Attendance");
		menuAction.put("Marks", "Marks");
		menuAction.put("Exam", "Exam");
		menuAction.put("Invoice", "Invoice");
		menuAction.put("Fees", "Fees");
		menuAction.put("Session", "Session");
		menuAction.put("Class", "Class");
		menuAction.put("Section", "Section");
		menuAction.put("Grading", "Grading");
		menuAction.put("Employee", "Employee");
		menuAction.put("Salary", "Salary");
		menuAction.put("Settings", "Settings");

		menuColors = new HashMap<>();

		menuButtons = new HashMap<>();

		int sizeOfMenu = menuList.size();
			sizeOfMenu = sizeOfMenu>1?sizeOfMenu:1;

		int heightOfMenuItem = sidebarHight/sizeOfMenu;
			heightOfMenuItem = heightOfMenuItem>50?50:heightOfMenuItem;

		for(String menu:menuList) {
			menuButtons.put(menu, new JButton(menu));
			if (menuButtons.containsKey(menu)) {
				menuButtons.get(menu).setIcon(Configuration.MENU_ICON);
				menuButtons.get(menu).setIconTextGap(15);
				menuButtons.get(menu).setModel(new FixedStateButtonModel());
				menuButtons.get(menu).setMaximumSize(new Dimension(Integer.MAX_VALUE, heightOfMenuItem));
				menuButtons.get(menu).setForeground(new Color(230, 230, 230));
				menuButtons.get(menu).setBackground(baseColor);
				menuButtons.get(menu).setBorder(BorderFactory.createMatteBorder(15, 25, 15, 15, new Color(0, 0, 0, 0)));
				menuButtons.get(menu).setFocusPainted(false);
				menuButtons.get(menu).setHorizontalAlignment(SwingConstants.LEFT);
				menuButtons.get(menu).setFont(Configuration.getFont(Configuration.FONT_LIGHT, Font.PLAIN, 18));
				menuButtons.get(menu).setCursor(new Cursor(Cursor.HAND_CURSOR));
				menuButtons.get(menu).addMouseListener(listenMouse());
				menuButtons.get(menu).addFocusListener(listenFocus());
				menuButtons.get(menu).addActionListener(listenAction());
				menuColors.put(menu, baseColor);
				this.add(menuButtons.get(menu));
			} 
		}

		selectMenu(menuButtons.get("Dashboard"));
		body.aclInvoke("Dashboard");
	}

	private ActionListener listenAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent ae){ 
				selectMenu((JButton)ae.getSource());
				body.aclInvoke(menuAction.get(((JButton)ae.getSource()).getText()));
			}  
		};
	}

	private MouseListener listenMouse() {
		Color in = hoverColor;
		return new MouseAdapter() {
			public void mouseClicked(MouseEvent me){
				createEffect((JButton)me.getSource(), in);
			}
			public void mousePressed(MouseEvent me){
				createEffect((JButton)me.getSource(), in);
			}
			public void mouseReleased(MouseEvent me){
				createEffect((JButton)me.getSource(), menuColors.get(((JButton) me.getSource()).getText()));
			}
			public void mouseEntered(MouseEvent me){
				createEffect((JButton)me.getSource(), in);
			}
			public void mouseExited(MouseEvent me){
				createEffect((JButton)me.getSource(), menuColors.get(((JButton) me.getSource()).getText()));
			}
		};
	}

	private FocusAdapter listenFocus() {
		return new FocusAdapter() {
			public void focusGained(FocusEvent fe){
				createEffect((JButton)fe.getSource(), hoverColor);
			}

			public void focusLost(FocusEvent fe){
				createEffect((JButton)fe.getSource(), baseColor);
			}
		};
	}

	private void createEffect(JButton source, Color color) {
		source.setBackground(color);
	}

	private void selectMenu(JButton selected) {
		Font normal = Configuration.getFont(Configuration.FONT_LIGHT, Font.PLAIN, 18);
		Font bold = Configuration.getFont(Configuration.FONT_REGULAR, Font.BOLD, 18);

		if(menuButtons.size()>0) {
			for(Map.Entry<String, JButton> element:menuButtons.entrySet()) {
				String target = element.getKey();
				JButton button = element.getValue();
				menuColors.put(target, baseColor);
				createEffect(button, baseColor);
				button.setFont(normal);
			}
		}

		menuColors.put(selected.getText(), selectedColor);
		createEffect(selected, selectedColor);
		selected.setFont(bold);
	}

	public class FixedStateButtonModel extends DefaultButtonModel {

        @Override
        public boolean isPressed() {
            return false;
        }

        @Override
        public boolean isRollover() {
            return false;
        }

        @Override
        public void setRollover(boolean b) {}
    }
}