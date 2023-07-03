package com.Ari_bilgi.Viev;

import com.Ari_bilgi.Database.DbConnection;
import com.Ari_bilgi.Halper.Halper;
import com.Ari_bilgi.Model.Instructor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InstructorProfilGUI extends JFrame{
    private JPanel wramper;
    private JTextField fld_inst_name;
    private JTextField fld_inst_phone;
    private JTextField fld_inst_gmail;
    private JTextField fld_inst_prof;
    private JButton btn_inst_panel;
    private JLabel lbl_inst_name;
    private JLabel lbl_inst_phone;
    private JLabel lbl_inst_gmail;
    private JLabel lbl_inst_prof;
    private JButton btn_Logout;
    private Instructor instructor;

    public InstructorProfilGUI(Instructor instructor){
        this.instructor = instructor;
        add(wramper);
        setSize(500, 500);
        setLocation(Halper.screenCenterPoint("x", getSize()), Halper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Halper.PROJECT_TITLE);
        setVisible(true);
        loadProf(instructor.getUname());
        fld_inst_name.setText(instructor.getName());
        fld_inst_prof.setText(instructor.getPhone());
        fld_inst_gmail.setText(instructor.getGmail());
        fld_inst_prof.setText(instructor.getProf());


        btn_inst_panel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InstructorPanelGUI panelGUI = new InstructorPanelGUI(instructor);
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
        String query = "SELECT name, phone, gmail, prof FROM aribilgi.instructor WHERE uname = ?";

        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, uname);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String gmail = rs.getString("gmail");
                String prof = rs.getString("prof");

                fld_inst_name.setText(name);
                fld_inst_phone.setText(phone);
                fld_inst_gmail.setText(gmail);
                fld_inst_prof.setText(prof);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
