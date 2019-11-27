package frames.panels.core;

import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.lang.reflect.*;

import library.*;
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

	public BodyPanel() {
		super();
		Construct();
	}

	public BodyPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		Construct();
	}

	public BodyPanel(LayoutManager layout) {
		super(layout);
		Construct();
	}

	public BodyPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
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

	private void __aclAddresser__Students() {
		this.removeAll();
		__aclInvoke__NavigationPane();
		this.add(new StudentsPanel(this, contentPosition, contentDim));
		this.revalidate();
		this.repaint();
	}

	private void __aclAddresser__AddNewStudent() {
		new AddStudentPopup((JFrame) SwingUtilities.getWindowAncestor(this));
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
		this.add(new SettingsPanel(contentPosition, contentDim));
		this.revalidate();
		this.repaint();
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

		JLabel sLabel = new JLabel("Sessions: 18-19, 19-20");
		sLabel.setFont( Configuration.getFont(Configuration.FONT_REGULAR, Font.BOLD, 12) );
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
				__aclInvoke__Error404();
			} catch (IllegalAccessException e) { 
			 	__aclInvoke__Error404();
			} catch (InvocationTargetException e) { 
				__aclInvoke__Error404();
			}
		}
		else
		{
			__aclInvoke__Error404();
		}
	}
}