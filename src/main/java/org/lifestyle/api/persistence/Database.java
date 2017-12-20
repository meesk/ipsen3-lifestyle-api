package org.lifestyle.api.persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Laurens Jan
 */
public class Database {
    private static String url = "jdbc:mysql://localhost:3306/groep06";
    private static String un = "root";
    private static String pw = "";
    
    public Database(){
        try{
           Class.forName("com.mysql.jdbc.Driver");
       }catch(ClassNotFoundException e){
           e.printStackTrace();
       }
    }
    
    public Connection getConnection(){
        try{
        return DriverManager.getConnection(url,un,pw);
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public void closeConnection(Connection con){
        try{
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
