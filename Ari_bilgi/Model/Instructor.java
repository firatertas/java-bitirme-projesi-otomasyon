package com.Ari_bilgi.Model;

import com.Ari_bilgi.Database.DbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Instructor {
    private int id;
    private String name;
    private String uname;
    private String pass;
    private String phone;
    private String gmail;
    private String prof;

    public Instructor() {
    }

    public Instructor(int id, String name, String uname, String pass, String phone, String gmail, String prof) {
        this.id = id;
        this.name = name;
        this.uname = uname;
        this.pass = pass;
        this.phone = phone;
        this.gmail = gmail;
        this.prof = prof;
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

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }
    public static ArrayList<Instructor> getList() {
        ArrayList<Instructor> instructorList = new ArrayList<>();
        String str = "SELECT * FROM aribilgi.instructor ";
        Instructor obj;
        try {

            Statement st = DbConnection.getInstance().createStatement();
            ResultSet rs = st.executeQuery(str);
            while (rs.next()) {
                obj = new Instructor();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setPhone(rs.getString("phone"));
                obj.setGmail(rs.getString("gmail"));
                obj.setProf(rs.getString("prof"));
                instructorList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return instructorList;
    }
    public static Instructor getFetch(String uname){
        Instructor obj = null;
        String query = "SELECT * FROM aribilgi.instructor WHERE uname=?";
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, uname);
            ResultSet rs = pr.executeQuery();

            while (rs.next()){
                obj =new Instructor();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setPhone(rs.getString("phone"));
                obj.setGmail(rs.getString("gmail"));
                obj.setProf(rs.getString("prof"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  obj;
    }
    public static Instructor getFetch(String uname, String pass){
        Instructor obj = null;
        String query = "SELECT * FROM aribilgi.instructor WHERE uname=? AND pass =?";
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, uname);
            pr.setString(2, pass);
            ResultSet rs = pr.executeQuery();

            while (rs.next()){
                obj =new Instructor();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setPhone(rs.getString("phone"));
                obj.setGmail(rs.getString("gmail"));
                obj.setProf(rs.getString("prof"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  obj;
    }
    public static Instructor getFetch(int id){
        Instructor obj = null;
        String query = "SELECT * FROM aribilgi.instructor WHERE id=  ?";
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();

            while (rs.next()){
                obj =new Instructor();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setPhone(rs.getString("phone"));
                obj.setGmail(rs.getString("gmail"));
                obj.setProf(rs.getString("prof"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  obj;
    }

    public static ArrayList<Instructor> searchIntructorlist(String query) {
        ArrayList<Instructor> instructorList = new ArrayList<>();
        Instructor obj;
        try {

            Statement st = DbConnection.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Instructor();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setPhone(rs.getString("phone"));
                obj.setGmail(rs.getString("gmail"));
                obj.setProf(rs.getString("prof"));
                instructorList.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return instructorList;
    }
    public static String searchQuery(String name, String phone, String prof){
        String query=" SELECT *FROM aribilgi.instructor WHERE phone LIKE '%{{phone}}%' AND name LIKE '%{{name}}%'";
        query=query.replace("{{phone}}", phone);
        query=query.replace("{{name}}", name);

        if (!prof.isEmpty()){
            query+="AND prof = '{{prof}}'";
            query=query.replace("{{prof}}", prof);
        }
        return query;
    }
}
