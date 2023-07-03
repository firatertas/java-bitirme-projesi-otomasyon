package com.Ari_bilgi.Model;

import com.Ari_bilgi.Database.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Lessons {
    private int id;
    private String name;

    public Lessons() {
    }

    public Lessons(int id, String name) {
        this.id = id;
        this.name = name;
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
    public static ArrayList<Lessons> getList(){
        ArrayList<Lessons> lessonsList = new ArrayList<>();
        Lessons obj ;
        String query = " SELECT * FROM aribilgi.lessons ";
        try {
            Statement st = DbConnection.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new Lessons();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                lessonsList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lessonsList;
    }
    public static Lessons getFetch(int id){
        Lessons obj = null;
        String query = "SELECT * FROM aribilgi.lessons WHERE id=  ?";
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Lessons();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
}
