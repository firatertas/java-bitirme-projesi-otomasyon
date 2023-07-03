package com.Ari_bilgi.Viev;


import com.Ari_bilgi.Halper.Halper;
import com.Ari_bilgi.Model.Course;
import com.Ari_bilgi.Model.Instructor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class InstructorPanelGUI extends JFrame {
    private JPanel wramper;
    private JPanel instructor_panel_top;
    private JPanel instructor_panel_btn;
    private JLabel lbl_instructor_wecome;
    private JButton btn_intructor_profil;
    private JButton btn_instructor_logout;
    private JTabbedPane pnl_instructor_less_list;
    private JScrollPane scr_instructor_list;
    private JTable tbl_instructor_list;
    private JButton btn_myNote;
    private DefaultTableModel mdl_instructor_list;
    private Object[] row_instructor_list;
    private  Instructor instructor;

    private int instructorId ;

    public InstructorPanelGUI (Instructor instructor){
        this.instructor = instructor;
        add(wramper);
        setSize(1000, 650);
        setLocation(Halper.screenCenterPoint("x", getSize()), Halper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Halper.PROJECT_TITLE);
        setVisible(true);
        lbl_instructor_wecome.setText(" Eğitmen : " + instructor.getName());

        mdl_instructor_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_instructor_list = {"ID", "Kurs Adı","Programlama Dili", "Eğitmen", "Başlama Tarihi", "Bitiş Tarihi"};
        mdl_instructor_list.setColumnIdentifiers(col_instructor_list);
        row_instructor_list = new Object[col_instructor_list.length];
        tbl_instructor_list.setModel(mdl_instructor_list);
        tbl_instructor_list.getTableHeader().setReorderingAllowed(false);
        tbl_instructor_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbl_instructor_list.getColumnModel().getColumn(0).setMaxWidth(50);

        loadCourses();
        btn_instructor_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AriBilgiLoginGUI ariBilgiLoginGUI = new AriBilgiLoginGUI();
                dispose();
            }
        });

        btn_myNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NoteGUI noteGUI = new NoteGUI(instructorId);
                dispose();
            }
        });

        btn_intructor_profil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Instructor instructor = Instructor.getFetch(instructorId);
                InstructorProfilGUI profilGUIlGUI = new InstructorProfilGUI(instructor);
                dispose();
            }
        });
    }
    private void loadCourses() {
        DefaultTableModel tableModel = (DefaultTableModel) tbl_instructor_list.getModel();
        tableModel.setRowCount(0);

        instructorId = instructor.getId();
        ArrayList<Course> courseList = Course.getListInst(instructorId);

        for (Course course : courseList) {
            String lessonName = "";
            if (course.getLessons() != null) {
                lessonName = course.getLessons().getName();
            }

            Object[] rowData = {
                    course.getId(),
                    lessonName,
                    course.getLang(),
                    course.getInstructor().getName(),
                    course.getStart_data(),
                    course.getFinish_data()
            };

            tableModel.addRow(rowData);
        }
    }
}


