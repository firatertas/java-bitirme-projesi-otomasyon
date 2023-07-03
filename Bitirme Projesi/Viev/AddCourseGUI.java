package com.Ari_bilgi.Viev;

import com.Ari_bilgi.Database.DbCrud;
import com.Ari_bilgi.Halper.Halper;
import com.Ari_bilgi.Halper.Item;
import com.Ari_bilgi.Model.Course;
import com.Ari_bilgi.Model.Instructor;
import com.Ari_bilgi.Model.Lessons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCourseGUI extends JFrame {
    private JPanel wramper;
    private JPanel pnl_btn_add;
    private JPanel pnl_top_ad;
    private JLabel lbl_add_explanation;
    private JLabel lbl_course_name;
    private JTextField fld_course_name;
    private JLabel lbl_course_lang;
    private JComboBox<String> cmn_course_lang;
    private JLabel lbl_course_instructor;
    private JComboBox<Item> cmb_course_instructor;
    private JTextField fld_course_startData;
    private JTextField fld_course_finishData;
    private JLabel lbl_course_startData;
    private JLabel lbl_course_finishData;
    private JButton btn_course_add;
    private JComboBox cmb_course_lessons;
    private static Course course;

    public AddCourseGUI(Course course) {
        this.course = course;

        add(wramper);
        setSize(450, 400);
        setLocation(Halper.screenCenterPoint("x", getSize()), Halper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Halper.PROJECT_TITLE);
        setVisible(true);
        loadInstructorCombo();
        loadLessonsCombo();
        loadLanguageCombo();

        btn_course_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item instructorItem = (Item) cmb_course_instructor.getSelectedItem();
                Item lessonsItem = (Item) cmb_course_lessons.getSelectedItem();
                String selectedLanguage = (String) cmn_course_lang.getSelectedItem();

                if (Halper.isFieldEmpty(fld_course_startData) || Halper.isFieldEmpty(fld_course_finishData)) {
                    Halper.showMsg("fill");
                } else if (DbCrud.addCourse(lessonsItem.getKey(), instructorItem.getKey(), fld_course_startData.getText(), fld_course_finishData.getText(), selectedLanguage)) {
                    Halper.showMsg("done");
                    loadInstructorCombo();
                    loadLessonsCombo();
                }

                dispose();
            }
        });
    }

    public void loadInstructorCombo() {
        cmb_course_instructor.removeAllItems();

        for (Instructor obj : Instructor.getList()) {
            cmb_course_instructor.addItem(new Item(obj.getId(), obj.getName()));
        }
    }
    public void loadLessonsCombo() {
        cmb_course_lessons.removeAllItems();

        for (Lessons obj : Lessons.getList()) {
            cmb_course_lessons.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    public void loadLanguageCombo() {
        cmn_course_lang.removeAllItems();
        cmn_course_lang.addItem("Java");
        cmn_course_lang.addItem("HTML");
        cmn_course_lang.addItem("C++");
        cmn_course_lang.addItem("C#");
        cmn_course_lang.addItem("Python");
        cmn_course_lang.addItem("JavaScript");
        cmn_course_lang.addItem("PHP");
    }
}
