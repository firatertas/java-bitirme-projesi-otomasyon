package com.Ari_bilgi.Model;

import com.Ari_bilgi.Database.DbConnection;

import java.sql.*;
import java.util.ArrayList;

public class Note {
    private int id;
    private String name;
    private String lang;
    private int instructor_id;
    private int course_id;
    private String note;
    private String date;
    private Instructor instructor;
    private Course course;
    private  String courseName;


    public Note() {
    }

    public Note(int id, String name, String lang, int instructor_id, int course_id, String note, String date, String courseName) {
        this.id = id;
        this.name = name;
        this.lang = lang;
        this.instructor_id = instructor_id;
        this.course_id = course_id;
        this.note = note;
        this.date = date;
        this.courseName= courseName;
        this.instructor = Instructor.getFetch(instructor_id);
        this.course = Course.getFetch(course_id);
    }

    public int getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(int instructor_id) {
        this.instructor_id = instructor_id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourse_id() {
        return course_id;
    }

    public String setCourse_id(int course_id) {
        this.course_id = course_id;
        return null;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static ArrayList<Note> getList() {
        ArrayList<Note> noteList = new ArrayList<>();
        String query =" SELECT * FROM aribilgi.notes";
        Note obj;
        try {

            Statement st = DbConnection.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Note();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setLang(rs.getString("lang"));
                obj.setInstructor_id(rs.getInt("instructor_id"));
                obj.setCourse_id(rs.getInt("lessons_id"));
                obj.setNote(rs.getString("note"));
                obj.setDate(rs.getString("date"));

                noteList.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return noteList;
    } public static ArrayList<Note> getListNot() {
        ArrayList<Note> noteList = new ArrayList<>();
        String query = "SELECT n.*, l.name AS 'course' FROM aribilgi.notes n, aribilgi.lessons l WHERE n.lessons_id = l.id";
        Note obj;
        try {

            Statement st = DbConnection.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Note();
                obj.setId(rs.getInt("id"));
                obj.setCourseName(rs.getString("course"));
                obj.setName(rs.getString("name"));
                obj.setLang(rs.getString("lang"));
                obj.setInstructor_id(rs.getInt("instructor_id"));
                obj.setCourse_id(rs.getInt("lessons_id"));
                obj.setNote(rs.getString("note"));
                obj.setDate(rs.getString("date"));

                noteList.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return noteList;
    }
    public static ArrayList<Note> getListIst(int instuctor_id) {

        ArrayList<Note> noteList = new ArrayList<>();
        String query = "SELECT n.*, l.name AS 'course' FROM aribilgi.notes n, aribilgi.lessons l WHERE n.lessons_id = l.id AND instructor_id= "+instuctor_id;
        Note obj;
        try {

            Statement st = DbConnection.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Note();
                obj.setId(rs.getInt("id"));
                obj.setCourseName(rs.getString("course"));
                obj.setName(rs.getString("name"));
                obj.setLang(rs.getString("lang"));
                obj.setInstructor_id(rs.getInt("instructor_id"));
                obj.setCourse_id(rs.getInt("lessons_id"));
                obj.setNote(rs.getString("note"));
                obj.setDate(rs.getString("date"));

                noteList.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return noteList;
    }
    public static ArrayList<Note> getListSTUD(int course_id) {
        ArrayList<Note> noteList = new ArrayList<>();
        String query = "SELECT n.*, l.name AS 'course' FROM aribilgi.notes n, aribilgi.lessons l WHERE n.lessons_id = l.id AND n.lessons_id = " +course_id;
        Note obj;
        try {
           Statement statement = DbConnection.getInstance().createStatement();
           ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                obj = new Note();
                obj.setId(rs.getInt("id"));
                obj.setCourseName(rs.getString("course"));
                obj.setName(rs.getString("name"));
                obj.setLang(rs.getString("lang"));
                obj.setInstructor_id(rs.getInt("instructor_id"));
                obj.setCourse_id(rs.getInt("lessons_id"));
                obj.setNote(rs.getString("note"));
                obj.setDate(rs.getString("date"));

                noteList.add(obj);
            }

            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return noteList;
    }


    public static Note getFetch(int id){
        Note obj= null;
        String query= "SELECT * FROM aribilgi.notes WHERE id = ?";

        try {
            PreparedStatement pr =DbConnection.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Note();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setLang(rs.getString("lang"));
                obj.setInstructor_id(rs.getInt("instructor_id"));
                obj.setCourse_id(rs.getInt("lessons_id"));
                obj.setDate(rs.getString("date"));
                obj.setNote(rs.getString("note"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
    public Course getCourses() {
        if (course == null) {
            course = Course.getFetch(course_id);
        }
        return course;
    }

}
