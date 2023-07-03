package com.Ari_bilgi.Viev;

import com.Ari_bilgi.Halper.Halper;
import com.Ari_bilgi.Model.Instructor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructorLoginGUI extends JFrame {
    private JPanel wramper;
    private JPanel wtop;
    private JPanel wbtn;
    private JTextField fld_instructor_uname;
    private JPasswordField fld_instructor_pass;
    private JButton btn_instructor_login;
    private JLabel lbl_instructor_uname;
    private JLabel lbl_instructor_pass;
    private JButton btn_ari_login;

    public InstructorLoginGUI(){
        add(wramper);
        setSize(500, 500);
        setLocation(Halper.screenCenterPoint("x", getSize()), Halper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Halper.PROJECT_TITLE);
        setVisible(true);
        btn_instructor_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Halper.isFieldEmpty(fld_instructor_uname) || Halper.isFieldEmpty(fld_instructor_pass)){
                    Halper.showMsg("fill");
                }else {
                    Instructor instLog = Instructor.getFetch(fld_instructor_uname.getText(), fld_instructor_pass.getText());
                    if (instLog == null){
                        Halper.showMsg("Kullanıcı Adı yada Şifresi Yanlış");
                    }else {
                        InstructorProfilGUI profilGUI = new InstructorProfilGUI(instLog);
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
