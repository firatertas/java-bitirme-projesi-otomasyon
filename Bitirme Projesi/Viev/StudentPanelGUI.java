package com.Ari_bilgi.Viev;

import com.Ari_bilgi.Halper.Halper;
import com.Ari_bilgi.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentPanelGUI extends JFrame {
    private JPanel wramper;
    private JPanel pnl_top;
    private JPanel pnl_btn;
    private JLabel lbl_student_wecome;
    private JButton btn_student_profil;
    private JButton btn_student_logout;
    private JScrollPane scr_student_list;
    private JTable tbl_student_course_list;
    private JScrollPane scr_student_note;
    private JTable tbl_student_note_list;
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;
    private DefaultTableModel mdl_note_list;
    private Object[] row_note_list;
    private Student student;
    private int lessonsID;
    private int courseId;

    public StudentPanelGUI(Student student) {
        this.student = student;
        add(wramper);
        setSize(1000, 650);
        setLocation(Halper.screenCenterPoint("x", getSize()), Halper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Halper.PROJECT_TITLE);
        setVisible(true);
        lbl_student_wecome.setText("Öğrenci : " + student.getName());


        //##Course##

        mdl_course_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };


        Object[] col_student_list = {"ID", "Kurs Adı", "Programlama Dili", "Eğitmen", "Başlama Tarihi", "Bitiş Tarihi"};
        mdl_course_list.setColumnIdentifiers(col_student_list);

        row_course_list = new Object[col_student_list.length];

        tbl_student_course_list.setModel(mdl_course_list);
        tbl_student_course_list.getTableHeader().setReorderingAllowed(false);

        tbl_student_course_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbl_student_course_list.getColumnModel().getColumn(0).setMaxWidth(50);
        loadCourses();

        //##Course##

        //##Note##

        mdl_note_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_instructor_list = {"ID", "Not Adı", "lang", "Kursun Adı", "Tarih", "Notun "};
        mdl_note_list.setColumnIdentifiers(col_instructor_list);
        row_note_list = new Object[col_instructor_list.length];
        tbl_student_note_list.setModel(mdl_note_list);
        tbl_student_note_list.getTableHeader().setReorderingAllowed(false);
        tbl_student_note_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbl_student_note_list.getColumnModel().getColumn(0).setMaxWidth(50);

        loadNoteModel();
        loadCourses();

        //##Note##
        btn_student_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AriBilgiLoginGUI ariBilgiLoginGUI = new AriBilgiLoginGUI();
                dispose();
            }
        });

        btn_student_profil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentProfilGUI profilGUI = new StudentProfilGUI(student);
                dispose();
            }
        });
    }
    private void loadCourses() {
        DefaultTableModel tableModel = (DefaultTableModel) tbl_student_course_list.getModel();
        tableModel.setRowCount(0);

        courseId = student.getId();
        ArrayList<Course> courseList = Course.getListLess(courseId);

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

    public void loadNoteModel() {
        DefaultTableModel tableModel = (DefaultTableModel) tbl_student_note_list.getModel();
        tableModel.setRowCount(0);
        Lessons lessons = Lessons.getFetch(lessonsID);
        int i;
        for (Note obj : Note.getListNot()) {
            i = 0;

            row_note_list[i++] = obj.getId();
            row_note_list[i++] = obj.getName();
            row_note_list[i++] = obj.getLang();
            Course course = obj.getCourses();
            if (course != null) {
                row_note_list[i++] = obj.getCourseName();
            }

            row_note_list[i++] = obj.getDate();
            row_note_list[i++] = obj.getNote();
            mdl_note_list.addRow(row_note_list);
        }

    }
}
