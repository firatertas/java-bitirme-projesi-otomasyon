package com.Ari_bilgi.Model;

import com.Ari_bilgi.Database.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class Course {
    private int id;
    private int lessons_id;
    private int instructor_id;
    private String lang;
    private String start_data;
    private String finish_data;
    private Instructor instructor;
    private Lessons lessons;

    public Course() {
    }


    public Course(int id, int lessons_id,  String lang, int instructor_id,  String start_data, String finish_data) {
        this.id = id;
        this.lessons_id = lessons_id;
        this.instructor_id = instructor_id;
        this.lang = lang;
        this.start_data = start_data;
        this.finish_data = finish_data;
        this.instructor = Instructor.getFetch(instructor_id);
        this.lessons = Lessons.getFetch(lessons_id);


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(int instructor_id) {
        this.instructor_id = instructor_id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getStart_data() {
        return start_data;
    }

    public void setStart_data(String start_data) {
        this.start_data = start_data;
    }

    public String getFinish_data() {
        return finish_data;
    }

    public void setFinish_data(String finish_data) {
        this.finish_data = finish_data;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public static ArrayList<Course> getList() {
        ArrayList<Course> courseList = new ArrayList<>();
        String query = "SELECT * FROM aribilgi.course";
        Course obj;
        try {
            Statement st = DbConnection.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Course();
                obj.setId(rs.getInt("id"));
                obj.setLessons_id(rs.getInt("lessons_id"));
                obj.setInstructor_id(rs.getInt("instructor_id"));
                obj.setLang(rs.getString("lang"));
                obj.setStart_data(rs.getString("start_data"));
                obj.setFinish_data(rs.getString("finish_data"));
                courseList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseList;
    }
    public static Course getFetch(int id) {
        Course obj = null;
        String query = "SELECT * FROM aribilgi.lessons WHERE id = ?";

        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Course();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    private void setName(String name) {
    }

    public static ArrayList<Course> getListInstuctor(int instructor_id) {
        ArrayList<Course> courseList = new ArrayList<>();
        String query = "SELECT * FROM aribilgi.course WHERE instructor_id = "+instructor_id;
        Course obj;
        try {
            Statement st = DbConnection.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Course();
                obj.setId(rs.getInt("id"));
                obj.setLessons_id(rs.getInt("lessons_id"));
                obj.setInstructor_id(rs.getInt("instructor_id"));
                obj.setLang(rs.getString("lang"));
                obj.setStart_data(rs.getString("start_data"));
                obj.setFinish_data(rs.getString("finish_data"));
                courseList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseList;
    }
    public static ArrayList<Course> searchCouserList(String query) {
        ArrayList<Course> courseList = new ArrayList<>();
        Course obj;
        try {
            Statement st = DbConnection.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Course();
                obj.setId(rs.getInt("id"));
                obj.setLessons_id(rs.getInt("lessons_id"));
                obj.setInstructor_id(rs.getInt("instructor_id"));
                obj.setLang(rs.getString("lang"));
                obj.setStart_data(rs.getString("start_data"));
                obj.setFinish_data(rs.getString("finish_data"));
                courseList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseList;
    }
    public static String searchQuery(String lang, String name) {
        String query = "SELECT c.*, l.name FROM aribilgi.course c, aribilgi.lessons l WHERE c.lessons_id = l.id AND c.lang LIKE '{{lang}}' OR l.name LIKE '%{{name}}%'";

        if (!name.isEmpty()) {
            query = query.replace("{{name}}", name);

        } if (!lang.isEmpty()) {
            query = query.replace("{{lang}}", lang);

        }
       return query;
  }

public static ArrayList<Course> getListInst(int instructorId) {
    ArrayList<Course> courseList = new ArrayList<>();

    try {

        String query = "SELECT * FROM aribilgi.course WHERE instructor_id = "+ instructorId;
        Statement statement = DbConnection.getInstance().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int courseId = resultSet.getInt("id");
            int lessons_id = resultSet.getInt("lessons_id");
            String lang = resultSet.getString("lang");
            int instructor_id = resultSet.getInt("instructor_id");
            String start_data = resultSet.getString("start_data");
            String finish_data = resultSet.getString("finish_data");

            Course course = new Course(courseId, lessons_id, lang, instructor_id, start_data, finish_data);
            courseList.add(course);
        }

        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return courseList;
}
    public static ArrayList<Course> getListLess(int lessonsId) {
        ArrayList<Course> courseList = new ArrayList<>();

        try {
            String query = "SELECT * FROM aribilgi.course WHERE lessons_id = "+ lessonsId;
            Statement statement = DbConnection.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int courseId = resultSet.getInt("id");
                int lessons_id = resultSet.getInt("lessons_id");
                String lang = resultSet.getString("lang");
                int instructor_id = resultSet.getInt("instructor_id");
                String start_data = resultSet.getString("start_data");
                String finish_data = resultSet.getString("finish_data");
                Course course = new Course(courseId, lessons_id, lang, instructor_id, start_data, finish_data);
                courseList.add(course);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courseList;
    }
    public static int getInstructorIdByName(String instructorName) {
        String query = "SELECT id FROM aribilgi.instructor WHERE name = ?";
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, instructorName);
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


