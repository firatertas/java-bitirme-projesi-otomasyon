package com.Ari_bilgi.Database;

import com.Ari_bilgi.Halper.Halper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private Connection connect=null;
    public Connection connectDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connect= DriverManager.getConnection(Halper.DB_URL,Halper.DB_USERNAME,Halper.DB_PASSWORD);
            return this.connect;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getInstance(){
        DbConnection db=new DbConnection();
        return db.connectDB();
    }


}
