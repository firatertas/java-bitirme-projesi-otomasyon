package com.Ari_bilgi.Database;

import com.Ari_bilgi.Halper.*;
import com.Ari_bilgi.Model.Course;
import com.Ari_bilgi.Model.Instructor;
import com.Ari_bilgi.Model.Operator;
import com.Ari_bilgi.Model.Student;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbCrud {

    //## Operator ##


    public static boolean operatorAdd(String name, String uname, String pass, String phone, String gmail) {
        String query = "INSERT INTO aribilgi.operator (name, uname, pass, phone, gmail) VALUES (?,?,?,?,?)";
        Operator findOperator = Operator.getFetch(uname);
        if (findOperator != null) {

            Halper.showMsg("Bu kullanıcı adı mevcut. Lütfen farklı bir kullanıcı adı giriniz.");
            return false;
        }
        boolean key = true;
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, uname);
            pr.setString(3, pass);
            pr.setString(4, phone);
            pr.setString(5, gmail);
            int risponse = pr.executeUpdate();
            if (risponse == -1) {
                Halper.showMsg("error");
            }
            key = risponse != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return key;
    }

    public static boolean updateOperator(int id, String name, String uname, String pass, String phone, String gmail) {
        String query = "UPDATE aribilgi.operator SET name=?, uname=?, pass=?, phone=?, gmail=? WHERE id=? ";
        Operator findOperator = Operator.getFetch(uname);
        if (findOperator != null && findOperator.getId() != id) {
            // Aynı kullanıcı adını kabul edemez.

            Halper.showMsg("Bu kullanıcı adı mevcut. Lütfen farklı bir kullanıcı adı giriniz.");
            return false;
        }
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, uname);
            pr.setString(3, pass);
            pr.setString(4, phone);
            pr.setString(5, gmail);
            pr.setInt(6, id);
            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteOperator(String name) {
        String query = "DELETE FROM aribilgi.operator WHERE name = ?";
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, name);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //## Operator ##
    //------------------------------------------------------------------------------
    //## Instructor ##

    public static boolean instructorAdd(String name, String uname, String pass, String phone, String gmail, String prof) {
        String query = "INSERT INTO aribilgi.instructor (name, uname, pass, phone, gmail, prof) VALUES (?,?,?,?,?,?)";
        Instructor findOperator = Instructor.getFetch(uname);
        if (findOperator != null) {
            // Aynı kullanıcı adını kabul edemez.

            Halper.showMsg("Bu kullanıcı adı mevcut. Lütfen farklı bir kullanıcı adı giriniz.");
            return false;
        }
        boolean key = true;
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, uname);
            pr.setString(3, pass);
            pr.setString(4, phone);
            pr.setString(5, gmail);
            pr.setString(6, prof);
            int risponse = pr.executeUpdate();
            if (risponse == -1) {
                Halper.showMsg("error");
            }
            key = risponse != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return key;
    }

    public static boolean deleteInstructor(int id) {
        String query = "DELETE FROM aribilgi.instructor WHERE id = ?";
        ArrayList<Course> courseList = Course.getListInstuctor(id);
        for (Course course : courseList) {
            DbCrud.deleteCourse(course.getId());
        }
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateInstructor(int id, String name, String uname, String pass, String phone, String gmail, String prof) {
        String query = " UPDATE aribilgi.instructor SET name=?, uname=?, pass=?, phone=?, gmail=?, prof=? WHERE id=? ";
        Instructor findInstuctor = Instructor.getFetch(uname);
        String[] instProf = {"java", "Html", "C#", "C++", "Python", "JavaScript", "PHP"};


        if (findInstuctor != null && findInstuctor.getId() != id) {
            Halper.showMsg("Bu kullanıcı adı mevcut. Lütfen farklı bir kullanıcı adı giriniz.");

            // ENUM değerinin kontrolü
            boolean isValidProf = false;
            for (String profession : instProf) {
                if (profession.equals(prof)) {
                    isValidProf = true;
                    break;
                }
            }

            if (!isValidProf) {
                Halper.showMsg("Yanlış bir profil girdiniz!");
                return false;
            }

            return false;


        }
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, uname);
            pr.setString(3, pass);
            pr.setString(4, phone);
            pr.setString(5, gmail);
            pr.setString(6, prof);
            pr.setInt(7, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            Halper.showMsg("Lütfen uzmanlık alanını doğru giriniz!");

            return false;
        }
    }
    //## Instructor ##
    //------------------------------------------------------------------------------
    //## Student ##

    public static boolean addStudent(String name, String uname, String pass, String phone, String gmail, int lessons_id) {
        String query = "INSERT INTO aribilgi.student (name, uname, pass, phone, gmail, lessons_id) VALUES (?,?,?,?,?,?)";
        Student findStudent = Student.getFetch(uname);
        if (findStudent != null) {
            // Aynı kullanıcı adını kabul edemez.

            Halper.showMsg("Bu kullanıcı adı mevcut. Lütfen farklı bir kullanıcı adı giriniz.");
            return false;
        }
        boolean key = true;
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, uname);
            pr.setString(3, pass);
            pr.setString(4, phone);
            pr.setString(5, gmail);
            pr.setInt(6, lessons_id);
            int risponse = pr.executeUpdate();
            if (risponse == -1) {
                Halper.showMsg("error");
            }
            key = risponse != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return key;
    }


    public static boolean updateStudent(int id, String name, String uname, String pass, String phone, String gmail, int lessons_id) {
        String query = "UPDATE aribilgi.student SET name=?, uname=?, pass=?, phone=?, gmail=?, lessons_id=? WHERE id=? ";
        Student findStudent = Student.getFetch(uname);
        if (findStudent != null && findStudent.getId() != id) {
            // Aynı kullanıcı adını kabul edemez.

            Halper.showMsg("Bu kullanıcı adı mevcut. Lütfen farklı bir kullanıcı adı giriniz.");
            return false;
        }
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, uname);
            pr.setString(3, pass);
            pr.setString(4, phone);
            pr.setString(5, gmail);
            pr.setInt(6, lessons_id);
            pr.setInt(7, id);
            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean deleteStudent(int id) {
        String query = "DELETE FROM aribilgi.student WHERE id = ?";

        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //## Student ##
    //------------------------------------------------------------------------------
    //## Course ##

    public static boolean addCourse(int lessons_id, int instructor_id, String finish_data, String start_data, String lang) {
        String query = "INSERT INTO aribilgi.course (lessons_id, instructor_id, start_data, finish_data, lang) VALUES (?,?,?,?,?) ";

        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setInt(1, lessons_id);
            pr.setInt(2, instructor_id);
            pr.setString(3, start_data);
            pr.setString(4, finish_data);
            pr.setString(5, lang);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean deleteCourse(int id) {
        String query = "DELETE FROM aribilgi.course WHERE id = ?";

        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateCourse(int id, int lessons_id , String lang, int instructor_id, String finish_data, String start_data) {
        String query = "UPDATE aribilgi.course SET lessons_id=?, lang=?, instructor_id=?, finish_data=?, start_data=? WHERE id=?";
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setInt(1, lessons_id);
            pr.setString(2, lang);
            pr.setInt(3, instructor_id);
            pr.setString(4, finish_data);
            pr.setString(5, start_data);
            pr.setInt(6, id);
            return pr.executeUpdate() != 0;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return true;
    }

    //## Course ##
    //------------------------------------------------------------------------------
    //## Lessons ##
    public static boolean addLessons(String name) {
        String query = "INSERT INTO aribilgi.lessons (name) VALUES (?) ";

        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, name);

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean updateLessons(int id, String name) {
        String query = "UPDATE aribilgi.lessons SET name=? WHERE id=?";
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setInt(2, id);
            return pr.executeUpdate() != 0;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return true;
    }
    public static boolean deleteLessons(int id) {
        String query = "DELETE FROM aribilgi.lessons WHERE id = ?";

        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //## Lessons ##
    //------------------------------------------------------------------------------
    //## Note ##
    public static boolean addNote(String name, String lang, String date, String note, int instructor_id, int lessons_id) {
        String query = "INSERT INTO aribilgi.notes (name, lang, note, date, instructor_id, lessons_id) VALUES (?,?,?,?,?,?)";
        boolean success = false;

        try {
             PreparedStatement preparedStatement = DbConnection.getInstance().prepareStatement(query) ;

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lang);
            preparedStatement.setString(3, note);
            preparedStatement.setString(4, date);
            preparedStatement.setInt(5, instructor_id);
            preparedStatement.setInt(6, lessons_id);

            int affectedRows = preparedStatement.executeUpdate();
            success = (affectedRows > 0);

            if (success) {
                Halper.showMsg("done");
            } else {
                Halper.showMsg("error");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return success;
    }

    public static boolean updateNote(int id, String name, String lang, String date, String note) {
        String query = "UPDATE aribilgi.notes SET name=?, lang=?,  date=?, note=? WHERE id=?";
        String[] instProf = {"java", "Html", "C#", "C++", "Python", "JavaScript", "PHP"};
        // ENUM değerinin kontrolü
        boolean isValidProf = false;
        for (String profession : instProf) {
            if (profession.equals(lang)) {
                isValidProf = true;
                break;
            }
        }
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, lang);
            pr.setString(3, date);
            pr.setString(4, note);
            pr.setInt(5, id);
            return pr.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteNote(int id) {
      String query = "DELETE FROM aribilgi.notes WHERE id=?";
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setInt(1, id);
           return pr.executeUpdate() !=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //## Note ##

}

