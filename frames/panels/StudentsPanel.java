package frames.panels;

import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.table.*;
import java.sql.SQLException;
import java.io.IOException;
import java.io.*;
import library.apachelang3.StringUtils;
import java.math.BigInteger;


import library.*;
import models.*;
import entity.*;
import frames.panels.core.*;

public class StudentsPanel extends TitledPanel {

	private Point coordinates;
	private BodyPanel bp;
    private ArrayList<SessionMeta> sessionMetas;
    private DefaultTableModel model;

	public StudentsPanel(BodyPanel bp, Point coordinates, Dimension dimension) {
		super(dimension);
		this.coordinates = coordinates;
		this.bp = bp;
		Construct();
	}

	public StudentsPanel(BodyPanel bp, Point coordinates, Dimension dimension, boolean isDoubleBuffered) {
		super(dimension, isDoubleBuffered);
		this.coordinates = coordinates;
		this.bp = bp;
		Construct();
	}

	public StudentsPanel(BodyPanel bp, Point coordinates, Dimension dimension, LayoutManager layout) {
		super(dimension, layout);
		this.coordinates = coordinates;
		this.bp = bp;
		Construct();
	}

	public StudentsPanel(BodyPanel bp, Point coordinates, Dimension dimension, LayoutManager layout, boolean isDoubleBuffered) {
		super(dimension, layout, isDoubleBuffered);
		this.coordinates = coordinates;
		this.bp = bp;
		Construct();
	}

	private void Construct() {

        model = new DefaultTableModel();
  
        JTable table = new JTable(model){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                switch(column) {
                    case 0: return ImageIcon.class;
                    default: return Object.class;
                }
            }
        };

        table.getTableHeader().setReorderingAllowed(false);

        model.addColumn("Photo");
        model.addColumn("Roll");
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("Phone");
        model.addColumn("Address");
        model.addColumn("Session");
        model.addColumn("Class");
        model.addColumn("Section");

        table.setRowHeight(50);
        Helper.setJTableColumnsWidth(table, (int) dimension.getWidth(), 5, 7, 15, 15, 11, 11, 11, 11, 11, 3);

        sessionMetas = null;

        drawTable();

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
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
          public void valueChanged(ListSelectionEvent e) {
            String selectedData = null;

            int selectedRow = table.getSelectedRow();
            if(flaq.get()) {
                if(sessionMetas.size()>selectedRow && selectedRow>=0) {
                    showOptions(sessionMetas.get(selectedRow));
                }
                flaq.set(false);
            } else {
                flaq.set(true);
            }
          }

        });
  
        // adding it to JScrollPane 
        JScrollPane sp = new JScrollPane(table); 
        sp.setLocation(locBody);
        sp.setSize(sizeBody); 
		
		this.add(sp);

		this.setTitle("Students");
		this.setBackground(new Color(255,255,255));
		this.setLocation(coordinates);
		this.setSize(dimension);
	}

    private void showOptions(SessionMeta sessionMeta) {
        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
        JDialog optionDialouge = new JDialog(parent, "Select Student Operation", true);

        JButton removeStudent = new JButton("REMOVE");
        JButton updateStudent = new JButton("UPDATE");

        removeStudent.setBounds(50, 50, 150, 50);
        updateStudent.setBounds(230, 50, 150, 50);


        removeStudent.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeStudent(sessionMeta);
                optionDialouge.dispose();
            }
        });

        updateStudent.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                optionDialouge.dispose();
            }
        });

        optionDialouge.add(removeStudent);
        optionDialouge.add(updateStudent);
        optionDialouge.setLayout(null);
        optionDialouge.setLocationRelativeTo(null);
        optionDialouge.setResizable(false);
        Dimension dimension = new Dimension(450, 170);
        Point location = new Point();
        location.setLocation((Configuration.mainWidth()/2) - (dimension.getWidth()/2), (Configuration.mainHeight()/2) - (dimension.getHeight()/2));
        optionDialouge.setSize(dimension);
        optionDialouge.setLocation(location);
        optionDialouge.setVisible(true);
    }

    private void removeStudent(SessionMeta sessionMeta) {
        BigInteger userid = sessionMeta.getUser();

        try{
            MSessionMeta mSessionMeta = new MSessionMeta();
            MUserMeta mUserMeta = new MUserMeta();
            MUser mUser = new MUser();

            UserMeta propicMeta = mUserMeta.retrive(userid, Configuration.META_PROFILE_PIC);
            String propic = propicMeta!=null ? propicMeta.getValue() : "";

            if(!StringUtils.isEmpty(propic)) {
                File pic = new File(Configuration.PATH_UPLOAD+propic);
                if(pic.exists()) {
                    pic.delete();
                }
            }

            mUserMeta.delete(userid);
            mSessionMeta.delete(sessionMeta);
            mUser.delete(new User(userid, null, null, null, null));

            reDrawTable();

        } catch(SQLException | DatabaseException ex) { System.out.println(ex.getMessage()); }
    }

    private void removeAllRow() {
        if (model.getRowCount() > 0) {
            for (int i = model.getRowCount() - 1; i > -1; i--) {
                model.removeRow(i);
            }
        }
    }

   private void drawTable() {
        try {
            MSessionMeta mSessionMeta = new MSessionMeta();
            MUser mUser = new MUser();
            MUserMeta mUserMeta = new MUserMeta();
            MSession mSession = new MSession();
            MClasses mClasses = new MClasses();
            MSection mSection = new MSection();

            sessionMetas = mSessionMeta.retrive();

            int i = 0;

            for(SessionMeta sessionMeta:sessionMetas) {
                User user = mUser.retrive(sessionMeta.getUser());
                HashMap<String, UserMeta> userMetas = mUserMeta.retrive(user);
                Session session = mSession.retrive(sessionMeta.getSession());
                Section section = mSection.retrive(sessionMeta.getSection());
                Classes _class = mClasses.retrive(section.getClassId());

                model.addRow(new Object[] { Helper.squareImage( Helper.bufferedImage(Helper.getAvatar(user)), 40 ), sessionMeta.getRoll(), userMetas.get(Configuration.META_FNAME).getValue() + " " + userMetas.get(Configuration.META_LNAME).getValue(), user.getEmail().get(), userMetas.get(Configuration.META_MOBILE).getValue(), userMetas.get(Configuration.META_ADDRESS).getValue(), ""+session.getStart()+"-"+session.getEnd(), _class.getAlphaName(), section.getAlphaName()});
            }

        } catch(SQLException | DatabaseException | IOException ex) { }
    }

    public void reDrawTable() {
        removeAllRow();
        drawTable();
    }

	protected MouseListener listenMouse() {
        StudentsPanel spanel = this;
		return new MouseAdapter() {
			public void mouseClicked(MouseEvent me){
                bp.setInvoker(spanel);
				bp.aclInvoke("AddNewStudent");
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