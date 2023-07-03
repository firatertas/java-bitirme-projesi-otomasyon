package com.Ari_bilgi.Viev;

import com.Ari_bilgi.Halper.Halper;
import com.Ari_bilgi.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteListGUI extends JFrame{
    private JPanel wramper;
    private JLabel lbl_note;
    private JScrollPane scr_note;
    private JTable tbl_note_list;
    private JButton btn_note_logout;
    private DefaultTableModel mdl_note_list;
    private Object[] row_note_list;
    private Operator operator;

    public NoteListGUI(Operator operator){
        this.operator = operator;
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
        tbl_note_list.getColumnModel().getColumn(5).setMaxWidth(100);
        loadNoteModel();
        btn_note_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                OperatorGUI operatorGUI = new OperatorGUI(operator);
            }
        });
    }
  public void loadNoteModel() {
        DefaultTableModel tableModel = (DefaultTableModel) tbl_note_list.getModel();
        tableModel.setRowCount(0);

        int i;
        for (Note obj : Note.getListNot()) {
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
