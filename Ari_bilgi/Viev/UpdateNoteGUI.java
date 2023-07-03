package com.Ari_bilgi.Viev;

import com.Ari_bilgi.Database.DbCrud;
import com.Ari_bilgi.Halper.Halper;
import com.Ari_bilgi.Halper.Item;
import com.Ari_bilgi.Model.Lessons;
import com.Ari_bilgi.Model.Note;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateNoteGUI extends JFrame {
    private JPanel wramper;
    private JPanel pnl_top;
    private JPanel pnl_btn;
    private JLabel lbl_note_update;
    private JTextField fld_note_name;
    private JComboBox cmb_note_lang;
    private JTextField fld_note_data;
    private JTextField txt_note;
    private JButton btn_note_add;
    private JLabel lbl_note_name;
    private JLabel lbl_note_lang;
    private JLabel lbl_note_data;
    private JLabel lbl_note_text;
    private JComboBox cmb_lsessons_name;
    private JLabel fld_lessons_name;
    private static Note note;
    private  int i;

    public UpdateNoteGUI(Note note) {
        this.note = note;
        add(wramper);
        setSize(400, 550);
        setLocation(Halper.screenCenterPoint("x", getSize()), Halper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Halper.PROJECT_TITLE);
        setVisible(true);
        fld_note_name.setText(note.getName());
        fld_note_data.setText(note.getDate());
        txt_note.setText(note.getNote());
        loadLanguageCombo();
        loadLessonsCombo();
        btn_note_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lang = (String) cmb_note_lang.getSelectedItem();

                try {
                    if (Halper.isFieldEmpty(fld_note_name) || Halper.isFieldEmpty(fld_note_data) || Halper.isFieldEmpty(txt_note)) {
                        Halper.showMsg("fill");
                    } else if (DbCrud.updateNote(note.getId(), fld_note_name.getText(), lang, fld_note_data.getText(), txt_note.getText())) {
                        Halper.showMsg("done");
                        loadNoteCombo();
                    }
                    dispose();
                }catch (Exception exception){ }

            }
        });
    }


    public void loadNoteCombo() {
        cmb_note_lang.removeAllItems();
        for (Note obj : Note.getList()) {
            cmb_note_lang.addItem(new Item(obj.getId(), obj.getLang()));
        }
    }
    public void loadLessonsCombo() {
        cmb_lsessons_name.removeAllItems();

        for (Lessons obj : Lessons.getList()) {
            cmb_lsessons_name.addItem(new Item(obj.getId(), obj.getName()));
        }
    }
    public void loadLanguageCombo() {
        cmb_note_lang.removeAllItems();
        cmb_note_lang.addItem("Java");
        cmb_note_lang.addItem("HTML");
        cmb_note_lang.addItem("C++");
        cmb_note_lang.addItem("C#");
        cmb_note_lang.addItem("Python");
        cmb_note_lang.addItem("JavaScript");
        cmb_note_lang.addItem("PHP");
    }
}
