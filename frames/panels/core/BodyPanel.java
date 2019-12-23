package frames.panels.core;

import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.lang.reflect.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.table.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


import library.*;
import entity.*;
import models.*;
import frames.LoginFrame;
import frames.panels.*;
import frames.panels.popups.*;

public class BodyPanel extends JPanel {

	private int bodyWidth;
	private int bodyHeight;
	private int contentWidth;
	private int contentHeight;
	private int contentMargin;
	private Point contentPosition;
	private Dimension contentDim;
	private User user;
	private HeaderPanel header;
	private JLabel sLabel;
	private ArrayList<Session> sessions;
	private Object invoker;


	public BodyPanel(User user) {
		super();
		this.user = user;
		Construct();
	}

	public BodyPanel(User user, boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		this.user = user;
		Construct();
	}

	public BodyPanel(User user, LayoutManager layout) {
		super(layout);
		this.user = user;
		Construct();
	}

	public BodyPanel(User user, LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		this.user = user;
		Construct();
	}

	private void Construct() {
		bodyWidth = Configuration.frameWidth()-Configuration.SIDEBAR_WIDTH;
		bodyHeight = Configuration.frameHeight()-Configuration.FOOTER_HEIGHT-Configuration.HEADER_HEIGHT;
		contentMargin = 15;
		contentPosition = new Point(contentMargin, 40+15);
		contentDim = new Dimension( bodyWidth - ( 2 * contentMargin), bodyHeight - 40 - ( 2 * contentMargin) );

		this.setLayout(null);
		__aclInvoke__NavigationPane();
	}

	public void setHeader(HeaderPanel header) {
		this.header = header;
	}

	private void __aclAddresser__Students() {
		this.removeAll();
		__aclInvoke__NavigationPane();
		this.add(new StudentsPanel(this, contentPosition, contentDim));
		this.revalidate();
		this.repaint();
	}

	private void __aclAddresser__AddNewStudent() {
		new AddStudentPopup((JFrame) SwingUtilities.getWindowAncestor(this), (StudentsPanel) invoker);
	}

	private void __aclAddresser__Attendance() {
		this.removeAll();
		__aclInvoke__NavigationPane();
		this.add(new AttendancePanel(this, contentPosition, contentDim));
		this.revalidate();
		this.repaint();
	}

	private void __aclAddresser__AddNewAttendance() {
		new AddAttendancePopup((JFrame) SwingUtilities.getWindowAncestor(this));
	}

	private void __aclAddresser__Marks() {
		this.removeAll();
		__aclInvoke__NavigationPane();
		this.add(new MarksPanel(this, contentPosition, contentDim));
		this.revalidate();
		this.repaint();
	}

	private void __aclAddresser__AddNewMarks() {
		new AddMarksPopup((JFrame) SwingUtilities.getWindowAncestor(this));
	}

	private void __aclAddresser__Exam() {
		this.removeAll();
		__aclInvoke__NavigationPane();
		this.add(new ExamPanel(this, contentPosition, contentDim));
		this.revalidate();
		this.repaint();
	}

	private void __aclAddresser__AddNewExam() {
		new AddExamPopup((JFrame) SwingUtilities.getWindowAncestor(this));
	}

	private void __aclAddresser__Invoice() {
		this.removeAll();
		__aclInvoke__NavigationPane();
		this.add(new InvoicePanel(this, contentPosition, contentDim));
		this.revalidate();
		this.repaint();
	}

	private void __aclAddresser__AddNewInvoice() {
		new AddInvoicePopup((JFrame) SwingUtilities.getWindowAncestor(this));
	}

	private void __aclAddresser__Fees() {
		this.removeAll();
		__aclInvoke__NavigationPane();
		this.add(new FeesPanel(this, contentPosition, contentDim));
		this.revalidate();
		this.repaint();
	}

	private void __aclAddresser__AddNewFees() {
		new AddFeesPopup((JFrame) SwingUtilities.getWindowAncestor(this));
	}

	private void __aclAddresser__Session() {
		this.removeAll();
		__aclInvoke__NavigationPane();
		this.add(new SessionPanel(this, contentPosition, contentDim));
		this.revalidate();
		this.repaint();
	}

	private void __aclAddresser__AddNewSession() {
		new AddSessionPopup((JFrame) SwingUtilities.getWindowAncestor(this));
	}


	private void __aclAddresser__Class() {
		this.removeAll();
		__aclInvoke__NavigationPane();
		this.add(new ClassPanel(this, contentPosition, contentDim));
		this.revalidate();
		this.repaint();
	}

	private void __aclAddresser__AddNewClass() {
		new AddClassPopup((JFrame) SwingUtilities.getWindowAncestor(this));
	}

	private void __aclAddresser__Section() {
		this.removeAll();
		__aclInvoke__NavigationPane();
		this.add(new SectionPanel(this, contentPosition, contentDim));
		this.revalidate();
		this.repaint();
	}

	private void __aclAddresser__AddNewSection() {
		new AddSectionPopup((JFrame) SwingUtilities.getWindowAncestor(this));
	}

	private void __aclAddresser__Grading() {
		this.removeAll();
		__aclInvoke__NavigationPane();
		this.add(new GradingPanel(this, contentPosition, contentDim));
		this.revalidate();
		this.repaint();
	}

	private void __aclAddresser__AddNewGrading() {
		new AddGradingPopup((JFrame) SwingUtilities.getWindowAncestor(this));
	}

	private void __aclAddresser__Employee() {
		this.removeAll();
		__aclInvoke__NavigationPane();
		this.add(new EmployeePanel(this, contentPosition, contentDim));
		this.revalidate();
		this.repaint();
	}

	private void __aclAddresser__AddNewEmployee() {
		new AddEmployeePopup((JFrame) SwingUtilities.getWindowAncestor(this));
	}

	private void __aclAddresser__Salary() {
		this.removeAll();
		__aclInvoke__NavigationPane();
		this.add(new SalaryPanel(this, contentPosition, contentDim));
		this.revalidate();
		this.repaint();
	}

	private void __aclAddresser__AddNewSalary() {
		new AddSalaryPopup((JFrame) SwingUtilities.getWindowAncestor(this));
	}

	private void __aclAddresser__Settings() {
		this.removeAll();
		__aclInvoke__NavigationPane();
		this.add(new SettingsPanel(user, (JFrame) SwingUtilities.getWindowAncestor(this), header, contentPosition, contentDim));
		this.revalidate();
		this.repaint();
	}

	private void __aclAddresser__Logout() {
		JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
		parent.setVisible(false);
	}

	private void __aclInvoke__Error404() {
		this.removeAll();
		__aclInvoke__NavigationPane();
		this.add(new Error404(contentPosition, contentDim));
		this.revalidate();
		this.repaint();
	}

	private void __aclInvoke__NavigationPane() {
		SpringLayout navigationLayout = new SpringLayout();
		JPanel navigationPane = new JPanel();
		navigationPane.setBounds(0,0, bodyWidth, 40);
		navigationPane.setLayout(navigationLayout);
		navigationPane.setBackground(new Color(218,222,230));
		BodyPanel bp = this;
		sLabel = new JLabel("Sessions: 18-19, 19-20");
		sLabel.setText(Helper.genDefSessionTitle());
		sLabel.setFont( Configuration.getFont(Configuration.FONT_REGULAR, Font.BOLD, 12) );
		sLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me){
				listenSessionLabel();
			}
			public void mousePressed(MouseEvent me){}
			public void mouseReleased(MouseEvent me){}
			public void mouseEntered(MouseEvent me){}
			public void mouseExited(MouseEvent me){}
			
		});
		navigationPane.add(sLabel);

		navigationLayout.putConstraint(SpringLayout.NORTH, sLabel, 12, SpringLayout.NORTH, navigationPane);
		navigationLayout.putConstraint(SpringLayout.EAST, sLabel, -15, SpringLayout.EAST, navigationPane);

		this.setBounds(Configuration.SIDEBAR_WIDTH, Configuration.HEADER_HEIGHT, bodyWidth, bodyHeight);
		this.add(navigationPane);
	}

	public void aclInvoke(String address) {
		String path = "__aclAddresser__" + address;
		Method callback = null;
		try {
			callback = this.getClass().getDeclaredMethod(path);
		} catch (SecurityException e) {  
		
		} catch (NoSuchMethodException e) { 
		
		}

		if(callback!=null) {
			try {
  				callback.invoke(this);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				__aclInvoke__Error404();
			} catch (IllegalAccessException e) {
				e.printStackTrace(); 
			 	__aclInvoke__Error404();
			} catch (InvocationTargetException e) { 
				e.printStackTrace();
				__aclInvoke__Error404();
			}
		}
		else
		{
			__aclInvoke__Error404();
		}
	}



	private void listenSessionLabel() {
		JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);

		DefaultTableModel tableModel;
		JComboBox<JcbItem<Session>> comboboxAcademicYear;

		JDialog sessionDialouge = new JDialog(parent, "Select Default Sessions", true);

		sessions = null;

		comboboxAcademicYear = new JComboBox<JcbItem<Session>>();
	    comboboxAcademicYear.setBounds(20, 20, 225, 30);

	    drawComboBox(comboboxAcademicYear);
	    JButton btnMakeDefault = new JButton("Make Default");
	    btnMakeDefault.setBounds(250, 20, 125, 30);

	    tableModel = new DefaultTableModel();
		JTable table = new JTable(tableModel){
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};

		class Flaq {
			private boolean flaq;

			public Flaq(boolean flaq) {
				this.flaq = flaq;
			}

			public void set(boolean flaq) {
				this.flaq = flaq;
			}

			public boolean get() {
				return this.flaq;
			}
		};

		Flaq flaq = new Flaq(true);

		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tableModel.addColumn("Click to Remove From Default");

		drawTable(tableModel);

		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	        String selectedData = null;

	        int selectedRow = table.getSelectedRow();
	        if(flaq.get()) {
	        	if(sessions.size()>selectedRow && selectedRow>=0) {
	        		removeDefault(comboboxAcademicYear, tableModel, sessions.get(selectedRow));
	        	}
	        	flaq.set(false);
	        } else {
	        	flaq.set(true);
	        }
	      }

	    });

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(
		    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(
		    JScrollPane.VERTICAL_SCROLLBAR_NEVER); 

		scrollPane.setBounds(20, 65, 355, 185);
		

		btnMakeDefault.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             	Session session = (Session) Helper.getSelectedValue(comboboxAcademicYear);
               	makeDefault(comboboxAcademicYear, tableModel, session);
            }
        });
	    sessionDialouge.add(scrollPane);
	    sessionDialouge.add(btnMakeDefault);
	    sessionDialouge.add(comboboxAcademicYear);
		sessionDialouge.setLayout(null);
		sessionDialouge.setLocationRelativeTo(null);
		sessionDialouge.setResizable(false);
		Dimension dimension = new Dimension(400, 300);
		Point location = new Point();
		location.setLocation((Configuration.mainWidth()/2) - (dimension.getWidth()/2), (Configuration.mainHeight()/2) - (dimension.getHeight()/2));
		sessionDialouge.setSize(dimension);
		sessionDialouge.setLocation(location);
		sessionDialouge.setVisible(true);
	}

	private void makeDefault(JComboBox<JcbItem<Session>> combobox, DefaultTableModel model, Session session) {
		if(session!=null) {
				try {
				session.setCached("1");
				MSession mSession = new MSession();
				mSession.update(session);
				reDrawTable(model);
				reDrawComboBox(combobox);
				sLabel.setText(Helper.genDefSessionTitle());
			} catch (DatabaseException | SQLException ex) {}
		}
	}

	private void removeDefault(JComboBox<JcbItem<Session>> combobox, DefaultTableModel model, Session session) {
		if(session!=null) {
			try {
				session.setCached("0");
				MSession mSession = new MSession();
				mSession.update(session);
				mSession.update(session);
				reDrawTable(model);
				reDrawComboBox(combobox);
				sLabel.setText(Helper.genDefSessionTitle());
			} catch (DatabaseException | SQLException ex) {}
		}
	}

	private void removeAll(DefaultTableModel model) {
		if (model.getRowCount() > 0) {
		    for (int i = model.getRowCount() - 1; i > -1; i--) {
		        model.removeRow(i);
		    }
		}
	}

	private void drawTable(DefaultTableModel model) {
		try{

			MSession mSession = new MSession();
			sessions = mSession.table("1");

			for(Session session:sessions) {
				model.addRow(new String[] { session.getStart() + "-" + session.getEnd() });
			}

		} catch(Exception ex) { }
	}

	private void reDrawTable(DefaultTableModel model) {
		removeAll(model);
		drawTable(model);
	}

	private void reDrawComboBox(JComboBox<JcbItem<Session>> combobox) {
		combobox.removeAllItems();
		drawComboBox(combobox);
	}

	private void drawComboBox(JComboBox<JcbItem<Session>> combobox) {
		HashMap<Integer, JcbItem<Session>> academicYears = null;

		try {
			MSession mSession = new MSession();
			academicYears = mSession.retrive(false);
			if(academicYears!=null && academicYears.size()>0) {
		    	Helper.initializeCombo(combobox, academicYears, null, "Select Academic Year");
		    } else {
		    	Helper.initializeCombo(combobox, null, null, "No Academic Year Found");
		    }
		} catch (DatabaseException | SQLException ex) {}
	}

	public void setInvoker(Object invoker) {
		this.invoker = invoker;
	}

	public Object getInvoker() {
		return this.invoker;
	}
}