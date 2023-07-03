package com.Ari_bilgi.Model;

import com.Ari_bilgi.Database.DbConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InstructorPanel {
    private int id;
    private  int cours_id;
    private int instructor_id;
    private String start;
    private String finish;
    private Course course;
    private  Instructor instructor;

    public InstructorPanel(int id, int cours_id, int instructor_id, String start, String finish) {
        this.id = id;
        this.cours_id = cours_id;
        this.instructor_id = instructor_id;
        this.start = start;
        this.finish = finish;
        this.instructor = Instructor.getFetch(instructor_id);
        this.course= Course.getFetch(instructor_id);
    }

    public InstructorPanel() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCours_id() {
        return cours_id;
    }

    public void setCours_id(int course_id) {
        this.cours_id = cours_id;
    }

    public int getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(int instructor_id) {
        this.instructor_id = instructor_id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public static ArrayList<InstructorPanel> getList() {
        ArrayList<InstructorPanel> instructorList = new ArrayList<>();
        String str = "SELECT * FROM aribilgi.panel_instructor ";
        InstructorPanel obj;
        try {

            Statement st = DbConnection.getInstance().createStatement();
            ResultSet rs = st.executeQuery(str);
            while (rs.next()) {
                obj = new InstructorPanel();
                obj.setId(rs.getInt("id"));
               obj.setCours_id(rs.getInt("cours_id"));
               obj.setInstructor_id(rs.getInt("instructor_id"));
                obj.setStart(rs.getString("start_data"));
                obj.setFinish(rs.getString("finish_data"));
                instructorList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return instructorList;
    }

}
