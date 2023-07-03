package com.Ari_bilgi.Model;

import com.Ari_bilgi.Database.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Student {
    private int id;
    private String name;
    private String uname;
    private String pass;
    private String phone;
    private String gmail;
    private int lessons_id;
    private Lessons lessons;

    public Student() {
    }

    public Student(int id, String name, String uname, String pass, String phone, String gmail, int lessons_id) {
        this.id = id;
        this.name = name;
        this.uname = uname;
        this.pass = pass;
        this.phone = phone;
        this.gmail = gmail;
        this.lessons_id = lessons_id;
        this.lessons = Lessons.getFetch(lessons_id);
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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public int getLessons_id() {
        return lessons_id;
    }

    public void setLessons_id(int lessons_id) {
        this.lessons_id = lessons_id;
    }

    public Lessons getLessons() {
        return lessons;
    }

    public void setLessons(Lessons lessons) {
        this.lessons = lessons;
    }

    public static ArrayList<Student> getList() {
        ArrayList<Student> studentList = new ArrayList<>();
        String str = "SELECT * FROM aribilgi.student ";
        Student obj;
        try {

            Statement st = DbConnection.getInstance().createStatement();
            ResultSet rs = st.executeQuery(str);
            while (rs.next()) {
                obj = new Student();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setPhone(rs.getString("phone"));
                obj.setGmail(rs.getString("gmail"));
                obj.setLessons_id(rs.getInt("lessons_id"));
                studentList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }
    public static ArrayList<Student> searchStudentList(String query) {
        ArrayList<Student> studentList = new ArrayList<>();
        Student obj;
        try {

            Statement st = DbConnection.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Student();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setPhone(rs.getString("phone"));
                obj.setGmail(rs.getString("gmail"));
                obj.setLessons_id(rs.getInt("lessons_id"));
                studentList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }
    public static String searchQuery(String name) {
        String query = " SELECT *FROM aribilgi.student WHERE name LIKE '%{{name}}%'";
        query = query.replace("{{name}}", name);

        return query;
    }
    public static Student getFetch(String uname){
        Student obj = null;
        String query = "SELECT * FROM aribilgi.student WHERE uname=?";
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, uname);
            ResultSet rs = pr.executeQuery();

            while (rs.next()){
                obj =new Student();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setPhone(rs.getString("phone"));
                obj.setGmail(rs.getString("gmail"));
                obj.setLessons_id(rs.getInt("lessons_id"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  obj;
    } public static Student getFetch(int id){
        Student obj = null;
        String query = "SELECT * FROM aribilgi.student WHERE id=?";
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();

            while (rs.next()){
                obj =new Student();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setPhone(rs.getString("phone"));
                obj.setGmail(rs.getString("gmail"));
                obj.setLessons_id(rs.getInt("lessons_id"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  obj;
    }
    public static Student getFetch(String uname, String pass){
        Student obj = null;
        String query = "SELECT * FROM aribilgi.student WHERE uname=? AND pass=?";
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, uname);
            pr.setString(2,pass);
            ResultSet rs = pr.executeQuery();

            while (rs.next()){
                obj =new Student();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setPhone(rs.getString("phone"));
                obj.setGmail(rs.getString("gmail"));
                obj.setLessons_id(rs.getInt("lessons_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  obj;
    }
    public static int getLessonsIdByName(String lessonsName) {
        String query = "SELECT id FROM aribilgi.lessons WHERE name = ?";
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, lessonsName);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
