package com.Ari_bilgi.Viev;

import com.Ari_bilgi.Database.DbConnection;
import com.Ari_bilgi.Halper.Halper;
import com.Ari_bilgi.Model.Course;
import com.Ari_bilgi.Model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentProfilGUI extends JFrame{
    private JPanel wramper;
    private JTextField fld_student_name;
    private JTextField fld_student_phone;
    private JTextField fld_student_gmail;
    private JButton btn_student_panel;
    private JLabel lbl_student_name;
    private JLabel lbl_student_phone;
    private JLabel lbl_student_gmail;
    private JButton btn_Logout;
    private JTable tbl_list_prof;
    private  int courseId;

    private Student student;

    public StudentProfilGUI(Student student) {
        this.student = student;
        add(wramper);
        setSize(500, 500);
        setLocation(Halper.screenCenterPoint("x", getSize()), Halper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Halper.PROJECT_TITLE);
        setVisible(true);
        loadProf(student.getUname());

        fld_student_name.setText(student.getName());
        fld_student_phone.setText(student.getPhone());
        fld_student_gmail.setText(student.getGmail());

        btn_student_panel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentPanelGUI panelGUI = new StudentPanelGUI(student);
                dispose();
            }
        });
        btn_Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AriBilgiLoginGUI ariBilgiLoginGUI = new AriBilgiLoginGUI();
                dispose();
            }
        });
    }
    public void loadProf(String uname) {
        String query = "SELECT name, phone, gmail, lessons_id FROM aribilgi.student WHERE uname = ?";

        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, uname);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String gmail = rs.getString("gmail");
                int lessons_id = rs.getInt("lessons_id");
                fld_student_name.setText(name);
                fld_student_phone.setText(phone);
                fld_student_gmail.setText(gmail);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadProfil() {
        DefaultTableModel tableModel = (DefaultTableModel) tbl_list_prof.getModel();
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
}
