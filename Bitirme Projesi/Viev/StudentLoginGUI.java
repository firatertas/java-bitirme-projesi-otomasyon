package com.Ari_bilgi.Viev;

import com.Ari_bilgi.Halper.Halper;
import com.Ari_bilgi.Model.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentLoginGUI extends JFrame {
    private JPanel wramper;
    private JPanel wtpo;
    private JPanel wbtn;
    private JPanel wtopp;
    private JLabel lbl_iconn;
    private JTextField fld_student_uname;
    private JPasswordField fld_student_pass;
    private JButton btn_student_login;
    private JLabel lbl_student_uname;
    private JLabel lbl_student_pass;
    private JButton btn_ari_login;

    public StudentLoginGUI() {
        add(wramper);
        setSize(500, 500);
        setLocation(Halper.screenCenterPoint("x", getSize()), Halper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Halper.PROJECT_TITLE);
        setVisible(true);


        btn_student_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Halper.isFieldEmpty(fld_student_uname) || Halper.isFieldEmpty(fld_student_pass)) {
                    Halper.showMsg("fill");
                } else
                {
                    Student student = Student.getFetch(fld_student_uname.getText(), fld_student_pass.getText());
                    if (student == null) {
                        Halper.showMsg("Kullanıcı Bulunamadı!");
                    } else {
                        StudentProfilGUI profilGUI = new StudentProfilGUI(student);

                        dispose();
                    }
                }

            }
        });
        btn_ari_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AriBilgiLoginGUI ariBilgiLoginGUI = new AriBilgiLoginGUI();
                dispose();
            }
        });
    }
}
