package com.Ari_bilgi.Viev;

import com.Ari_bilgi.Halper.Halper;
import com.Ari_bilgi.Model.Operator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperatorLoginGUI extends JFrame {
    private JPanel wramper;
    private JTextField fld_operator_uname;
    private JPasswordField fld_operator_pass;
    private JButton btn_operator_login;
    private JLabel lbl_operator_uname;
    private JLabel lbl_operator_pass;
    private JButton btn_ari_login;

    public OperatorLoginGUI() {
        add(wramper);
        setSize(500, 500);
        setLocation(Halper.screenCenterPoint("x", getSize()), Halper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Halper.PROJECT_TITLE);
        setVisible(true);
        btn_operator_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Halper.isFieldEmpty(fld_operator_uname) || Halper.isFieldEmpty(fld_operator_pass)) {
                    Halper.showMsg("fill");
                } else {
                    Operator operator = Operator.getFetch(fld_operator_uname.getText(), fld_operator_pass.getText());
                    if (operator == null) {
                        Halper.showMsg("Kullanıcı Bulunamadı!");
                    } else {
                        OperatorProfilGUI profilGUI = new OperatorProfilGUI(operator);

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

