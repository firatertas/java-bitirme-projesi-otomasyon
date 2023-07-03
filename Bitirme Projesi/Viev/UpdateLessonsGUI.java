package com.Ari_bilgi.Viev;

import com.Ari_bilgi.Database.DbCrud;
import com.Ari_bilgi.Halper.Halper;
import com.Ari_bilgi.Model.Lessons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateLessonsGUI extends JFrame {
    private JPanel wramper;
    private JButton btn_uptade;
    private JTextField fld_lesson_name;
    private Lessons lessons;

    public UpdateLessonsGUI(Lessons lessons) {
        this.lessons = lessons;
        add(wramper);
        setSize(300, 150);
        setLocation(Halper.screenCenterPoint("x", getSize()), Halper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Halper.PROJECT_TITLE);
        setVisible(true);

        fld_lesson_name.setText(lessons.getName());


        btn_uptade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Halper.isFieldEmpty(fld_lesson_name)){
                    Halper.showMsg("fill");
                } else if (DbCrud.updateLessons(lessons.getId(), fld_lesson_name.getText())) {
                    Halper.showMsg("done");
                }
                dispose();
            }
        });
    }

}
