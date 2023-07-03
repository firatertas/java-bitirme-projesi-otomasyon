package com.Ari_bilgi.Model;

import com.Ari_bilgi.Database.DbConnection;

import java.sql.*;
import java.util.ArrayList;

public class Operator  {
    private int id;
    private String name;
    private String uname;
    private String pass;
    private String phone;
    private String gmail;

    public Operator() {
    }

    public Operator(int id, String name, String uname, String pass, String phone, String gmail) {
        this.id = id;
        this.name = name;
        this.uname = uname;
        this.pass = pass;
        this.phone = phone;
        this.gmail = gmail;
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

    public static ArrayList<Operator> getlist() {
        ArrayList<Operator> operatorList = new ArrayList<>();
        String str = "SELECT * FROM aribilgi.operator ";
        Operator obj;
        try {

            Statement st = DbConnection.getInstance().createStatement();
            ResultSet rs = st.executeQuery(str);
            while (rs.next()) {
                obj = new Operator();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setPhone(rs.getString("phone"));
                obj.setGmail(rs.getString("gmail"));
                operatorList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return operatorList;
    }
    public static Operator getFetch(String uname, String pass){
        Operator obj= null;
        String query= "SELECT * FROM aribilgi.operator WHERE uname = ? AND pass=?";

        try {
            PreparedStatement pr =DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, uname);
            pr.setString(2, pass);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Operator();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setPhone(rs.getString("phone"));
                obj.setGmail(rs.getString("gmail"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
   }
    public static Operator getFetch(String uname) {
        Operator obj = null;
        String query = "SELECT * FROM aribilgi.operator WHERE uname = ?";

        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, uname);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                obj = new Operator();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setPhone(rs.getString("phone"));
                obj.setGmail(rs.getString("gmail"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
    public static Operator getFetch(int id){
        Operator obj= null;
        String query= "SELECT * FROM aribilgi.operator WHERE id = ? ";

        try {
            PreparedStatement pr =DbConnection.getInstance().prepareStatement(query);
            pr.setInt(1, id);

            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Operator();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setPhone(rs.getString("phone"));
                obj.setGmail(rs.getString("gmail"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static ArrayList<Operator> searchOperatorList(String query) {
        ArrayList<Operator> searchOperator = new ArrayList<>();

        Operator obj;
        try {

            Statement st = DbConnection.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Operator();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setPhone(rs.getString("phone"));
                obj.setGmail(rs.getString("gmail"));
                searchOperator.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return searchOperator;
    }
    public static String searchQuery(String name) {
        String query = "SELECT * FROM aribilgi.operator WHERE name LIKE '%" + name + "%' ";
        return query;
    }
}

