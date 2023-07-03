package com.Ari_bilgi.Viev;

import com.Ari_bilgi.Halper.Halper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AriBilgiLoginGUI extends JFrame{
    private JPanel wramper;
    private JPanel wButton;
    private JPanel wTop;
    private JButton btn_operator_login;
    private JButton btn_student_lagin;
    private JButton btn_instructor_login;

    public AriBilgiLoginGUI(){
        add(wramper);
        setSize(500, 500);
        setLocation(Halper.screenCenterPoint("x", getSize()), Halper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Halper.PROJECT_TITLE);
        setVisible(true);
        btn_operator_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OperatorLoginGUI operatorLoginGUI = new OperatorLoginGUI();
                dispose();
            }
        });
        btn_instructor_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InstructorLoginGUI instructorLoginGUI = new InstructorLoginGUI();
                dispose();
            }
        });
        btn_student_lagin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                StudentLoginGUI studentLoginGUI = new StudentLoginGUI();
                dispose();
            }
        });
    }
}
