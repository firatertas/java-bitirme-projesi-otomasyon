package com.Ari_bilgi.Viev;

import com.Ari_bilgi.Database.DbCrud;
import com.Ari_bilgi.Halper.Halper;
import com.Ari_bilgi.Halper.Item;
import com.Ari_bilgi.Model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

import static com.Ari_bilgi.Model.Course.getInstructorIdByName;
import static com.Ari_bilgi.Model.Student.getLessonsIdByName;

public class OperatorGUI extends JFrame {
    private JPanel wramper;
    private JTabbedPane tab_operator;
    private JLabel lbl_operetor_welcome;
    private JPanel pnl_top;
    private JButton btn_operator_profile;
    private JButton btn_operator_logout;
    private JPanel pnl_operator_list;
    private JScrollPane scr_operator_list;
    private JTable tbl_operator_list;
    private JPanel pnl_operator_form;
    private JTextField fld_operator_name;
    private JTextField fld_operator_uname;
    private JTextField fld_operator_pass;
    private JTextField fld_operator_phone;
    private JTextField fld_operator_mail;
    private JButton btn_operator_add;
    private JTextField fld_operator_delete;
    private JButton btn_operator_delete;
    private JLabel lbl_operator_name;
    private JLabel lbl_operator_uname;
    private JLabel lbl_operator_pass;
    private JLabel lbl_operator_phone;
    private JLabel lbl_operator_gmail;
    private JLabel lbl_operator_delete;
    private JTextField fld_search_opertor_name;
    private JButton btn_operator_search;
    private JScrollPane scrl_instructor_list;
    private JTable tbl_instructor_list;
    private JTextField fld_instructor_name;
    private JTextField fld_instructor_uname;
    private JTextField fld_instructor_pass;
    private JTextField fld_instructor_phone;
    private JTextField fld_instructor_gmail;
    private JLabel lbl_instructor_name;
    private JLabel lbl_instructor_uname;
    private JLabel lbl_instructor_pass;
    private JLabel lbl_instructor_phone;
    private JLabel lbl_instructor_gmail;
    private JLabel lbl_instructor_prof;
    private JComboBox cmb_instructor_prof;
    private JButton btb_instructor_add;
    private JTextField fld_instructor_id;
    private JButton btn_instructor_delete;
    private JLabel lbl_instructor_delete;
    private JTextField fld_sh_instructor_name;
    private JLabel lbl_sh_instructor_name;
    private JLabel lbl_sh_instructor_prof;
    private JComboBox cmb_sh_instructor_prof;
    private JTextField fld_sh_instructor_phone;
    private JButton btn_instructor_sh;
    private JLabel lbl_sh_instructor_phone;
    private JScrollPane scrl_student_list;
    private JTable tbl_student_list;
    private JScrollPane scrl_course_list;
    private JTable tbl_course_list;
    private JButton btn_course_add;
    private JButton btn_course_delet;
    private JPanel pnl_course_list;
    private JLabel lbl_student_name;
    private JTextField fld_student_name;
    private JLabel lbl_student_uname;
    private JTextField fld_student_uname;
    private JLabel lbl_student_pass;
    private JTextField fld_student_pass;
    private JLabel lbl_student_phone;
    private JTextField fld_student_phone;
    private JLabel lbl_student_gmail;
    private JTextField fld_student_gmail;
    private JLabel lbl_student_course;
    private JTextField fld_student_course;
    private JButton btn_student_add;
    private JButton btn_student_delete;
    private JTextField fld_course_sh_name;
    private JComboBox cmb_course_sh_lang;
    private JButton btn_course_sh;
    private JLabel lbl_couser_sh_name;
    private JLabel lbl_course_sh_lang;
    private JPanel pnl_sh;
    private JPanel pnl_search;
    private JTextField fld_student_sh_name;
    private JButton btn_student_search;
    private JLabel lbl_student_sh_name;
    private JButton dersNotlarıButton;
    private JScrollPane scr_lessons_list;
    private JTable tbl_lessons_list;
    private JButton btn_lessons_add;
    private JButton btn_lessuns_update;
    private JButton btn_lessons_delete;
    private JLabel lbl_lessons_name;
    private JTextField fld_lessons_name;
    private JComboBox cmb_student_lessons;
    private DefaultTableModel mdl_operator_list;
    private Object[] row_operator_list;
    private DefaultTableModel mdl_instructor_list;
    private Object[] row_instructor_list;
    private DefaultTableModel mdl_student_list;
    private Object[] row_student_list;
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;
    private DefaultTableModel mdl_lessons_list;
    private Object[] row_lessons_list;
    private static Operator operator;



    public OperatorGUI(Operator operator) {
        this.operator = operator;

        add(wramper);
        setSize(1000, 650);
        setLocation(Halper.screenCenterPoint("x", getSize()), Halper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Halper.PROJECT_TITLE);
        setVisible(true);

        lbl_operetor_welcome.setText("Operator : " + operator.getName());

        mdl_operator_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        // ## Operator ##

        Object[] col_operator_list = {"ID", "Ad Soyad", "Kullanıcı Adı", "Şifre", "Telefon No", "Gmail"};
        mdl_operator_list.setColumnIdentifiers(col_operator_list);

        row_operator_list = new Object[col_operator_list.length];
        loadOperatorModel();

        tbl_operator_list.setModel(mdl_operator_list);
        tbl_operator_list.getTableHeader().setReorderingAllowed(false);

        tbl_operator_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbl_operator_list.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_operator_list.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tbl_operator_list.getSelectedRow();
                    if (selectedRow != -1) {
                        String selectedOperatorName = tbl_operator_list.getValueAt(selectedRow, 1).toString();
                        fld_operator_delete.setText(selectedOperatorName);
                    }
                }
            }
        });

        tbl_operator_list.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int operator_id = Integer.parseInt(tbl_operator_list.getValueAt(tbl_operator_list.getSelectedRow(), 0).toString());
                    String operator_name = tbl_operator_list.getValueAt(tbl_operator_list.getSelectedRow(), 1).toString();
                    String operator_uname = tbl_operator_list.getValueAt(tbl_operator_list.getSelectedRow(), 2).toString();
                    String operator_pass = tbl_operator_list.getValueAt(tbl_operator_list.getSelectedRow(), 3).toString();
                    String operator_phone = tbl_operator_list.getValueAt(tbl_operator_list.getSelectedRow(), 4).toString();
                    String operator_gmail = tbl_operator_list.getValueAt(tbl_operator_list.getSelectedRow(), 5).toString();
                    if (DbCrud.updateOperator(operator_id, operator_name, operator_uname, operator_pass, operator_phone, operator_gmail)) {
                        Halper.showMsg("done");

                    }
                    loadOperatorModel();
                    loadCourseModel();
                    loadInstructorModel();
                }
            }
        });
        // ## Operator ##


        // ## Instructor ##

        mdl_instructor_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_instructor_list = {"ID", "Ad Soyad", "Kullanıcı Adı", "Şifre", "Telefon No", "Gmail", "Uzmanlık Alanı"};
        mdl_instructor_list.setColumnIdentifiers(col_instructor_list);
        row_instructor_list = new Object[col_instructor_list.length];
        loadInstructorModel();
        tbl_instructor_list.setModel(mdl_instructor_list);
        tbl_instructor_list.getTableHeader().setReorderingAllowed(false);

        tbl_instructor_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbl_instructor_list.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_instructor_list.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    String select_inst_delete = tbl_instructor_list.getValueAt(tbl_instructor_list.getSelectedRow(), 0).toString();
                    fld_instructor_id.setText(select_inst_delete);
                } catch (Exception exception) {

                }
            }
        });

        tbl_instructor_list.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int instructor_id = Integer.parseInt(tbl_instructor_list.getValueAt(tbl_instructor_list.getSelectedRow(), 0).toString());
                    String instructor_name = tbl_instructor_list.getValueAt(tbl_instructor_list.getSelectedRow(), 1).toString();
                    String instructor_uname = tbl_instructor_list.getValueAt(tbl_instructor_list.getSelectedRow(), 2).toString();
                    String instructor_pass = tbl_instructor_list.getValueAt(tbl_instructor_list.getSelectedRow(), 3).toString();
                    String instructor_phone = tbl_instructor_list.getValueAt(tbl_instructor_list.getSelectedRow(), 4).toString();
                    String instructor_gmail = tbl_instructor_list.getValueAt(tbl_instructor_list.getSelectedRow(), 5).toString();
                    String instructor_prof = tbl_instructor_list.getValueAt(tbl_instructor_list.getSelectedRow(), 6).toString();
                    if (DbCrud.updateInstructor(instructor_id, instructor_name, instructor_uname, instructor_pass, instructor_phone, instructor_gmail, instructor_prof)) {
                        Halper.showMsg("done");

                    }
                    loadInstructorModel();
                    loadCourseModel();
                }
            }
        });

        // ## Instructor ##

        // ## Student ##

        mdl_student_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        mdl_student_list = new DefaultTableModel();
        Object[] col_student_list = {"ID", "Ad Soyad", "Kullanıcı Adı", "Şifre", "Telefon Numarası", "Gmail", "Kurs Adı "};
        mdl_student_list.setColumnIdentifiers(col_student_list);
        row_student_list = new Object[col_student_list.length];

        tbl_student_list.setModel(mdl_student_list);

        tbl_student_list.getTableHeader().setReorderingAllowed(false);
        tbl_student_list.getColumnModel().getColumn(0).setMaxWidth(50);
        loadStudentModel();
        loadLessonsCombo();
        //loadLessonsModel();
        tbl_student_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tbl_student_list.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int student_id = Integer.parseInt(tbl_student_list.getValueAt(tbl_student_list.getSelectedRow(), 0).toString());
                    String student_name = tbl_student_list.getValueAt(tbl_student_list.getSelectedRow(), 1).toString();
                    String student_uname = tbl_student_list.getValueAt(tbl_student_list.getSelectedRow(), 2).toString();
                    String student_pass = tbl_student_list.getValueAt(tbl_student_list.getSelectedRow(), 3).toString();
                    String student_phone = tbl_student_list.getValueAt(tbl_student_list.getSelectedRow(), 4).toString();
                    String student_gmail = tbl_student_list.getValueAt(tbl_student_list.getSelectedRow(), 5).toString();
                    String lessonsName = tbl_student_list.getValueAt(tbl_student_list.getSelectedRow(), 6).toString();
                    int lessonsId = getLessonsIdByName(lessonsName);
                    if (DbCrud.updateStudent(student_id, student_name, student_uname, student_pass, student_phone, student_gmail, lessonsId)) {
                        Halper.showMsg("done");

                    }
                    loadStudentModel();
                    loadLessonsModel();

                }
            }
        });


        // ## Student ##


        // ## Course ##

        mdl_course_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_course_list = {"ID", "Kurs Adı", " Program Dili", "Eğitmen", "Başlama Tarihi", "Bitiş Tarihi"};
        mdl_course_list.setColumnIdentifiers(col_course_list);
        row_course_list = new Object[col_course_list.length];

        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_course_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        loadCourseModel();
        loadLessonsCombo();
        tbl_course_list.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {

                    int course_id = Integer.parseInt(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 0).toString());

                    String lessonsName = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 3).toString();
                    int lessonsId = getInstructorIdByName(lessonsName);
                    String course_lang = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 2).toString();
                    String instructorName = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 3).toString();
                    int instructorId = getInstructorIdByName(instructorName);
                    String course_start = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 4).toString();
                    String course_finish = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 5).toString();

                    if (DbCrud.updateCourse(course_id, lessonsId, course_lang, instructorId, course_start, course_finish)) {
                        Halper.showMsg("done");
                    }
                    loadStudentModel();
                    loadInstructorModel();

                }
            }
        });


        // ## Course ##

        //## Lessons ##

        mdl_lessons_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_lessons_list = {"ID", "Eğitim Adı"};
        mdl_lessons_list.setColumnIdentifiers(col_lessons_list);
        row_lessons_list = new Object[col_lessons_list.length];

        tbl_lessons_list.setModel(mdl_lessons_list);
        tbl_lessons_list.getTableHeader().setReorderingAllowed(false);
        tbl_lessons_list.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_lessons_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        loadLessonsModel();

        //## Lessons ##

        btn_operator_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Halper.isFieldEmpty(fld_operator_name) || Halper.isFieldEmpty(fld_operator_uname) || Halper.isFieldEmpty(fld_operator_pass) || Halper.isFieldEmpty(fld_operator_phone) || Halper.isFieldEmpty(fld_operator_mail)) {
                    Halper.showMsg("fill");
                } else {
                    String name = fld_operator_name.getText();
                    String uname = fld_operator_uname.getText();
                    String pass = fld_operator_pass.getText();
                    String phone = fld_operator_phone.getText();
                    String gmail = fld_operator_mail.getText();

                    if (DbCrud.operatorAdd(name, uname, pass, phone, gmail)) {
                        Halper.showMsg("done");
                        loadOperatorModel();
                        fld_operator_mail.setText(null);
                        fld_operator_phone.setText(null);
                        fld_operator_pass.setText(null);
                        fld_operator_uname.setText(null);
                        fld_operator_name.setText(null);
                    }
                }
            }
        });
        btn_operator_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AriBilgiLoginGUI ariBilgiLoginGUI = new AriBilgiLoginGUI();
                dispose();
            }
        });
        btn_operator_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Halper.isFieldEmpty(fld_operator_delete)) {
                    Halper.showMsg("fill");
                } else if (Halper.confirm("sure")) {
                    int selectedRow = tbl_operator_list.getSelectedRow();
                    if (selectedRow != -1) {
                        String selectedOperatorName = tbl_operator_list.getValueAt(selectedRow, 1).toString();
                    }

                    if (DbCrud.deleteOperator(fld_operator_delete.getText())) {
                        Halper.showMsg("done");
                        loadOperatorModel();
                    } else {
                        Halper.showMsg("error");
                    }
                }
            }
        });
        btn_operator_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = fld_search_opertor_name.getText();
                fld_search_opertor_name.setText("");
                refreshOperatorList();

                String searchQuery = Operator.searchQuery(name);
                ArrayList<Operator> searchingOperator = Operator.searchOperatorList(searchQuery);
                loadOperatorSearchModel(searchingOperator);
            }
        });

        btb_instructor_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*

                 */
                if (Halper.isFieldEmpty(fld_instructor_name) || Halper.isFieldEmpty(fld_instructor_uname) || Halper.isFieldEmpty(fld_instructor_pass) || Halper.isFieldEmpty(fld_instructor_phone) || Halper.isFieldEmpty(fld_instructor_gmail)) {
                    Halper.showMsg("fill");
                } else {
                    String name = fld_instructor_name.getText();
                    String uname = fld_instructor_uname.getText();
                    String pass = fld_instructor_pass.getText();
                    String phone = fld_instructor_phone.getText();
                    String gmail = fld_instructor_gmail.getText();
                    String prof = cmb_instructor_prof.getSelectedItem().toString();
                    if (DbCrud.instructorAdd(name, uname, pass, phone, gmail, prof)) {
                        Halper.showMsg("done");
                        loadInstructorModel();
                        loadCourseModel();
                        fld_instructor_name.setText(null);
                        fld_instructor_uname.setText(null);
                        fld_instructor_pass.setText(null);
                        fld_instructor_phone.setText(null);
                        fld_instructor_gmail.setText(null);
                    }
                }
            }
        });
        btn_instructor_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Halper.isFieldEmpty(fld_instructor_id)) {
                    Halper.showMsg("fill");
                } else if (Halper.confirm("DİKKAT : Bu Eğitmeni silmek istediğinize eminmisiniz? Devam eden kursları olabilir.")) {
                    int instructor_id = Integer.parseInt(fld_instructor_id.getText());
                    if (DbCrud.deleteInstructor(instructor_id)) {
                        Halper.showMsg("done");
                        loadInstructorModel();
                        loadCourseModel();
                        fld_instructor_id.setText(null);
                    }
                }
            }
        });


        btn_instructor_sh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = fld_sh_instructor_name.getText();
                String phone = fld_sh_instructor_phone.getText();
                String prof = cmb_sh_instructor_prof.getSelectedItem().toString();
                String query = Instructor.searchQuery(name, phone, prof);

                loadInstructorSearchModel(Instructor.searchIntructorlist(query));

            }
        });


        btn_course_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Course c = new Course();
                AddCourseGUI addCourseGUI = new AddCourseGUI(c);
                addCourseGUI.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadCourseModel();
                    }
                });
            }
        });


        btn_course_delet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Halper.confirm("sure")) {
                    int seletd_id = Integer.parseInt(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 0).toString());
                    if (DbCrud.deleteCourse(seletd_id)) {
                        Halper.showMsg("done");
                        loadCourseModel();
                    }
                }
            }
        });
        btn_student_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Halper.isFieldEmpty(fld_student_name) || Halper.isFieldEmpty(fld_student_uname) || Halper.isFieldEmpty(fld_student_pass) || Halper.isFieldEmpty(fld_student_phone) || Halper.isFieldEmpty(fld_student_gmail)) {
                    Halper.showMsg("fill");
                } else {
                    String name = fld_student_name.getText();
                    String uname = fld_student_uname.getText();
                    String pass = fld_student_pass.getText();
                    String phone = fld_student_phone.getText();
                    String gmail = fld_student_gmail.getText();
//                    int lessons_id = Integer.parseInt(fld_student_course.getText());
                    Item lessonsItem = (Item) cmb_student_lessons.getSelectedItem();
                    if (DbCrud.addStudent(name, uname, pass, phone, gmail, lessonsItem.getKey())) {
                        Halper.showMsg("done");
                        loadStudentModel();
                        fld_student_name.setText(null);
                        fld_student_uname.setText(null);
                        fld_student_pass.setText(null);
                        fld_student_phone.setText(null);
                        fld_student_gmail.setText(null);
                    }
                }
            }
        });
        btn_student_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    if (Halper.confirm("sure")) {
                        int select_id = Integer.parseInt(tbl_student_list.getValueAt(tbl_student_list.getSelectedRow(), 0).toString());
                        if (DbCrud.deleteStudent(select_id)) {
                            Halper.showMsg("done");
                            loadStudentModel();
                        } else {
                            Halper.showMsg("error");
                        }
                    }
                } catch (Exception exception) {

                }


            }
        });

        btn_course_sh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = fld_course_sh_name.getText();
                String lang = cmb_course_sh_lang.getSelectedItem().toString();
                String quey = Course.searchQuery(lang, name);
                ArrayList<Course> searchCourse = Course.searchCouserList(quey);
                loadSearchCourseModel(searchCourse);
            }
        });
        btn_student_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = fld_student_sh_name.getText();
                String quey = Student.searchQuery(name);
                ArrayList<Student> searchStudent = Student.searchStudentList(quey);
                loadSearchStudentModel(searchStudent);
            }
        });


        btn_lessons_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Halper.isFieldEmpty(fld_lessons_name)) {
                    Halper.showMsg("fill");
                } else if (DbCrud.addLessons(fld_lessons_name.getText())) {
                    Halper.showMsg("done");
                    loadLessonsModel();
                    fld_lessons_name.setText(null);

                }
            }
        });
        btn_lessuns_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select_id = Integer.parseInt(tbl_lessons_list.getValueAt(tbl_lessons_list.getSelectedRow(), 0).toString());
                UpdateLessonsGUI lessonsGUI = new UpdateLessonsGUI(Lessons.getFetch(select_id));
                lessonsGUI.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadLessonsModel();

                    }
                });
            }
        });
        btn_lessons_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select = Integer.parseInt(tbl_lessons_list.getValueAt(tbl_lessons_list.getSelectedRow(), 0).toString());
                if (DbCrud.deleteLessons(select)) {
                    Halper.showMsg("done");
                    loadLessonsModel();
                }
            }
        });
        btn_operator_profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int operatorID = operator.getId();
                Operator op = Operator.getFetch(operatorID);
                OperatorProfilGUI profilGUI = new OperatorProfilGUI(operator);
                dispose();
            }
        });
        dersNotlarıButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                NoteListGUI noteListGUI = new NoteListGUI(operator);
                dispose();
            }
        });
    }

    public void refreshOperatorList() {

        DefaultTableModel tableModel = (DefaultTableModel) tbl_operator_list.getModel();
        tableModel.setRowCount(0); // Tabloyu temizle


        ArrayList<Operator> operatorList = Operator.getlist(); // Tüm operatörleri al
        for (Operator obj : operatorList) {
            Object[] row_operator_list = new Object[6];
            row_operator_list[0] = obj.getId();
            row_operator_list[1] = obj.getName();
            row_operator_list[2] = obj.getUname();
            row_operator_list[3] = obj.getPass();
            row_operator_list[4] = obj.getPhone();
            row_operator_list[5] = obj.getGmail();
            tableModel.addRow(row_operator_list);
        }
    }

    public void loadOperatorSearchModel(ArrayList<Operator> searchList) {
        DefaultTableModel tableModel = (DefaultTableModel) tbl_operator_list.getModel();
        tableModel.setRowCount(0);
        int i;
        for (Operator obj : searchList) {
            i = 0;
            row_operator_list[i++] = obj.getId();
            row_operator_list[i++] = obj.getName();
            row_operator_list[i++] = obj.getUname();
            row_operator_list[i++] = obj.getPass();
            row_operator_list[i++] = obj.getPhone();
            row_operator_list[i++] = obj.getGmail();
            mdl_operator_list.addRow(row_operator_list);
        }
    }

    public void loadStudentModel() {
        DefaultTableModel tableModel = (DefaultTableModel) tbl_student_list.getModel();
        tableModel.setRowCount(0);
        for (Student obj : Student.getList()) {
            int i = 0;
            row_student_list[i++] = obj.getId();
            row_student_list[i++] = obj.getName();
            row_student_list[i++] = obj.getUname();
            row_student_list[i++] = obj.getPass();
            row_student_list[i++] = obj.getPhone();
            row_student_list[i++] = obj.getGmail();
            Lessons lessons = Lessons.getFetch(obj.getLessons_id());
            if (lessons != null) {
                row_student_list[i++] = lessons.getName();
            } else {
                row_student_list[i++] = "";
            }
            mdl_student_list.addRow(row_student_list);
        }
    }

    public void loadLessonsCombo() {
        cmb_student_lessons.removeAllItems();

        for (Lessons obj : Lessons.getList()) {
            cmb_student_lessons.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    public void loadSearchStudentModel(ArrayList<Student> list) {
        DefaultTableModel tableModel = (DefaultTableModel) tbl_student_list.getModel();
        tableModel.setRowCount(0);
        for (Student obj : list) {
            int i = 0;
            row_student_list[i++] = obj.getId();
            row_student_list[i++] = obj.getName();
            row_student_list[i++] = obj.getUname();
            row_student_list[i++] = obj.getPass();
            row_student_list[i++] = obj.getPhone();
            row_student_list[i++] = obj.getGmail();
            Lessons lessons = Lessons.getFetch(obj.getLessons_id());
            if (lessons != null) {
                row_student_list[i++] = lessons.getName();
            } else {
                row_student_list[i++] = "";
            }
            mdl_student_list.addRow(row_student_list);
        }
    }

    public void loadCourseModel() {
        DefaultTableModel tableModel = (DefaultTableModel) tbl_course_list.getModel();
        tableModel.setRowCount(0);

        for (Course obj : Course.getList()) {
            int i = 0;
            row_course_list[i++] = obj.getId();
            Lessons lessons = Lessons.getFetch(obj.getLessons_id());
            if (lessons != null) {
                row_course_list[i++] = lessons.getName();
            } else {
                row_course_list[i++] = "";
            }
            row_course_list[i++] = obj.getLang();
            Instructor instructor = Instructor.getFetch(obj.getInstructor_id());
            if (instructor != null) {
                row_course_list[i++] = instructor.getName();
            } else {
                row_course_list[i++] = "";
            }
            row_course_list[i++] = obj.getStart_data();
            row_course_list[i++] = obj.getFinish_data();
            mdl_course_list.addRow(row_course_list);
        }
    }

    public void loadLessonsModel() {
        DefaultTableModel tableModel = (DefaultTableModel) tbl_lessons_list.getModel();
        tableModel.setRowCount(0);
        int i;
        for (Lessons obj : Lessons.getList()) {
            i = 0;
            row_lessons_list[i++] = obj.getId();
            row_lessons_list[i++] = obj.getName();

            mdl_lessons_list.addRow(row_lessons_list);
        }
    }

    public void loadSearchCourseModel(ArrayList<Course> list) {
        DefaultTableModel tableModel = (DefaultTableModel) tbl_course_list.getModel();
        tableModel.setRowCount(0);

        for (Course obj : list) {
            int i = 0;
            row_course_list[i++] = obj.getId();
            Lessons lessons = Lessons.getFetch(obj.getLessons_id());
            if (lessons != null) {
                row_course_list[i++] = lessons.getName();
            } else {
                row_course_list[i++] = "";
            }
            row_course_list[i++] = obj.getLang();
            Instructor instructor = Instructor.getFetch(obj.getInstructor_id());
            if (instructor != null) {
                row_course_list[i++] = instructor.getName();
            } else {
                row_course_list[i++] = "";
            }
            row_course_list[i++] = obj.getStart_data();
            row_course_list[i++] = obj.getFinish_data();
            mdl_course_list.addRow(row_course_list);
        }
    }

    public void loadOperatorModel() {
        DefaultTableModel tableModel = (DefaultTableModel) tbl_operator_list.getModel();
        tableModel.setRowCount(0);
        int i;
        for (Operator obj : Operator.getlist()) {
            i = 0;
            row_operator_list[i++] = obj.getId();
            row_operator_list[i++] = obj.getName();
            row_operator_list[i++] = obj.getUname();
            row_operator_list[i++] = obj.getPass();
            row_operator_list[i++] = obj.getPhone();
            row_operator_list[i++] = obj.getGmail();
            mdl_operator_list.addRow(row_operator_list);
        }
    }

    public void loadInstructorModel() {
        DefaultTableModel tableModel = (DefaultTableModel) tbl_instructor_list.getModel();
        tableModel.setRowCount(0);
        int i;
        for (Instructor obj : Instructor.getList()) {
            i = 0;
            row_instructor_list[i++] = obj.getId();
            row_instructor_list[i++] = obj.getName();
            row_instructor_list[i++] = obj.getUname();
            row_instructor_list[i++] = obj.getPass();
            row_instructor_list[i++] = obj.getPhone();
            row_instructor_list[i++] = obj.getGmail();
            row_instructor_list[i++] = obj.getProf();
            mdl_instructor_list.addRow(row_instructor_list);
        }
    }

    public void loadInstructorSearchModel(ArrayList<Instructor> searchList) {
        DefaultTableModel tableModel = (DefaultTableModel) tbl_instructor_list.getModel();
        tableModel.setRowCount(0);

        int i;
        for (Instructor obj : searchList) {

            i = 0;
            row_instructor_list[i++] = obj.getId();
            row_instructor_list[i++] = obj.getName();
            row_instructor_list[i++] = obj.getUname();
            row_instructor_list[i++] = obj.getPass();
            row_instructor_list[i++] = obj.getPhone();
            row_instructor_list[i++] = obj.getGmail();
            row_instructor_list[i++] = obj.getProf();
            mdl_instructor_list.addRow(row_instructor_list);
        }
    }
}
