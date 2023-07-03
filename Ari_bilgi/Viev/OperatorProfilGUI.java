package com.Ari_bilgi.Viev;

import com.Ari_bilgi.Database.DbConnection;
import com.Ari_bilgi.Halper.Halper;
import com.Ari_bilgi.Model.Operator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OperatorProfilGUI extends JFrame {
    private JPanel wramper;
    private JTextField txt_name;
    private JTextField txt_phone;
    private JTextField txt_gmail;
    private JButton btn_panel_login;
    private JButton btn_exit;
    private JLabel lbl_name;
    private JLabel lbl_phone;
    private JLabel lbl_gmail;
    private Operator operator;

    public OperatorProfilGUI(Operator operator) {
        this.operator = operator;
        add(wramper);
        setSize(500, 500);
        setLocation(Halper.screenCenterPoint("x", getSize()), Halper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Halper.PROJECT_TITLE);
        setVisible(true);
        loadProf(operator.getUname());
        txt_name.setText(operator.getName());
        txt_phone.setText(operator.getPhone());
        txt_gmail.setText(operator.getGmail());

        btn_panel_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OperatorGUI operatorGUI = new OperatorGUI(operator);
                dispose();
            }
        });

        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AriBilgiLoginGUI ariBilgiLoginGUI = new AriBilgiLoginGUI();
                dispose();
            }
        });

    }
    public void loadProf(String uname) {
        String query = "SELECT name, phone, gmail FROM aribilgi.operator WHERE uname = ?";

        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, uname);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String gmail = rs.getString("gmail");

                txt_name.setText(name);
                txt_phone.setText(phone);
                txt_gmail.setText(gmail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
