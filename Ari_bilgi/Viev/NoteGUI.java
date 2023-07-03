package com.Ari_bilgi.Viev;

import com.Ari_bilgi.Database.DbCrud;
import com.Ari_bilgi.Halper.Halper;
import com.Ari_bilgi.Model.Course;
import com.Ari_bilgi.Model.Instructor;
import com.Ari_bilgi.Model.Note;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NoteGUI extends JFrame {
    private JPanel wramper;
    private JPanel pnl_top;
    private JPanel pnl_button;
    private JLabel lbl_note;
    private JButton btn_profil;
    private JButton btn_logout;
    private JButton btn_back;
    private JScrollPane scr_note;
    private JTable tbl_note_list;
    private JButton btn_nete_new;
    private JButton btn_note_update;
    private JButton btn_note_delete;
    private DefaultTableModel mdl_note_list;
    private Object[] row_note_list;
    private int instructorId;


    public NoteGUI(int instructorId) {
        this.instructorId=instructorId;
        add(wramper);
        setSize(1000, 650);
        setLocation(Halper.screenCenterPoint("x", getSize()), Halper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Halper.PROJECT_TITLE);
        setVisible(true);

        mdl_note_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_instructor_list = {"ID", "Not Adı", "lang", "Eğitmen Adı", "Kurs Adı", "Tarih", "Notun "};
        mdl_note_list.setColumnIdentifiers(col_instructor_list);
        row_note_list = new Object[col_instructor_list.length];
        tbl_note_list.setModel(mdl_note_list);
        tbl_note_list.getTableHeader().setReorderingAllowed(false);
        tbl_note_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbl_note_list.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_note_list.getColumnModel().getColumn(2).setMaxWidth(100);
        loadNoteModel();
        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Instructor instructor = Instructor.getFetch(instructorId);
                InstructorPanelGUI instructorPanelGUI = new InstructorPanelGUI(instructor);
                dispose();
            }
        });

        btn_nete_new.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Note note = new Note();
                Instructor instLog = Instructor.getFetch(instructorId);
                NewNoteGUI noteGUI = new NewNoteGUI(instLog);
                loadNoteModel();
                noteGUI.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadNoteModel();
                    }
                });
            }
        });
        btn_note_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int note_id = Integer.parseInt(tbl_note_list.getValueAt(tbl_note_list.getSelectedRow(), 0).toString());
                UpdateNoteGUI updateNoteGUI = new UpdateNoteGUI(Note.getFetch(note_id));
                updateNoteGUI.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadNoteModel();
                    }
                });
            }
        });
        btn_note_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Halper.confirm("sure")) {
                    try {
                        int note_id = Integer.parseInt(tbl_note_list.getValueAt(tbl_note_list.getSelectedRow(), 0).toString());
                        if (DbCrud.deleteNote(note_id)) {
                            Halper.showMsg("done");
                            loadNoteModel();
                        } else {
                            Halper.showMsg("error");
                        }

                    } catch (Exception exception) {  }
                }
            }
        });
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AriBilgiLoginGUI ariBilgiLoginGUI = new AriBilgiLoginGUI();
                dispose();
            }
        });
        btn_profil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Instructor instructor = Instructor.getFetch(instructorId);
                InstructorProfilGUI profilGUIlGUI = new InstructorProfilGUI(instructor);
                dispose();
            }
        });
    }
    public void loadNoteModel() {
        DefaultTableModel tableModel = (DefaultTableModel) tbl_note_list.getModel();
        tableModel.setRowCount(0);

        int i;
        for (Note obj : Note.getListIst(instructorId)) {
            i = 0;

            row_note_list[i++] = obj.getId();
            row_note_list[i++] = obj.getName();
            row_note_list[i++] = obj.getLang();
            Instructor instructor = Instructor.getFetch(obj.getInstructor_id());
            if (instructor != null) {
                row_note_list[i++] = instructor.getName();
            }

            Course course = obj.getCourses();
            if (course != null) {
                row_note_list[i++] = obj.getCourseName();
            }

            row_note_list[i++] = obj.getDate();
            row_note_list[i++] = obj.getNote();
            mdl_note_list.addRow(row_note_list);
        }
    }
}
