package com.Ari_bilgi.Viev;

import com.Ari_bilgi.Database.DbCrud;
import com.Ari_bilgi.Halper.Halper;
import com.Ari_bilgi.Halper.Item;
import com.Ari_bilgi.Model.Course;
import com.Ari_bilgi.Model.Instructor;
import com.Ari_bilgi.Model.Lessons;
import com.Ari_bilgi.Model.Note;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewNoteGUI extends JFrame {
    private JPanel wramper;
    private JTextField fld_note_name;
    private JComboBox cmb_note_lang;
    private JTextField fld_note_date;
    private JTextField txt_note;
    private JButton btn_note_add;
    private JPanel pnl_top;
    private JPanel pnl_button;
    private JLabel lbl_note_name;
    private JLabel lbl_note_lang;
    private JLabel lbl_note_date;
    private JLabel lbl_note_write;
    private JComboBox cmb_course_name;
    private JLabel lbl_course_name;
    private JLabel lbl_instructor_name;
    private JComboBox cmb_instructor_name;
    private ActionListener saveButtonListener;
    private static Note note;
    private  Instructor instructor;
    private Course course;
    int instructorID;


    public NewNoteGUI(Instructor instructor) {
        this.instructor = instructor;
        //this.instructorID = Instructor.getFetch();
        add(wramper);
        setSize(400, 550);
        setLocation(Halper.screenCenterPoint("x", getSize()), Halper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Halper.PROJECT_TITLE);
        setVisible(true);

        loadLessonsCombo();
        loadInstructorCombo();


        btn_note_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Halper.isFieldEmpty(fld_note_name) || Halper.isFieldEmpty(fld_note_date) || Halper.isFieldEmpty(txt_note)) {
                    Halper.showMsg("fill");
                } else {
                    Item courseItem = (Item) cmb_course_name.getSelectedItem();
                    Item instructorItem = (Item) cmb_instructor_name.getSelectedItem();

                    String name = fld_note_name.getText();
                    String lang = cmb_note_lang.getSelectedItem().toString();
                    String date = fld_note_date.getText();
                    String note = txt_note.getText();

                    if (courseItem != null && instructorItem != null) {
                        int courseId = courseItem.getKey();
                        int instructorId = instructorItem.getKey();

                        if (DbCrud.addNote(name, lang, date, note, instructorId, courseId)) {
                            Halper.showMsg("done");

                        } else {
                            Halper.showMsg("error");
                        }
                        dispose();
                    }
                }
            }
        });
    }

    public void loadLessonsCombo() {
        cmb_course_name.removeAllItems();

        for (Lessons obj : Lessons.getList()) {
            cmb_course_name.addItem(new Item(obj.getId(), obj.getName()));
        }
    } public void loadInstructorCombo() {
        cmb_instructor_name.removeAllItems();

        for (Instructor obj : Instructor.getList()) {
            cmb_instructor_name.addItem(new Item(obj.getId(), obj.getName()));
        }
    }
}
