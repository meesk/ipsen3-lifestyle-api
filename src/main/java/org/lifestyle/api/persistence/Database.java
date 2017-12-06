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
    private static String url = "jdbc:postgresql://localhost:5432/ipsen3";
    private static String un = "postgres";
    private static String pw = "Covoe123456";
    
    public Database(){
        try{
           Class.forName("org.postgresql.Driver");
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
