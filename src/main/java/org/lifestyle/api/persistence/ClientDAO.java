package org.lifestyle.api.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.lifestyle.api.model.Client;

@Singleton
public class ClientDAO {
    
    private final ArrayList<Client> allClients;
    private final Database db;
    
    @Inject
    public ClientDAO(){
       allClients = new ArrayList();
       db = new Database();
       
       
       
       
//       Client client = new Client();
//       client.setFirstName("Barry");
//       client.setLastName("Pieters");
//       client.setHeight(185);
//       client.setWeight(80);
//       client.setPhoneNumber("0618481481");
//       
//       Client client2 = new Client();
//       client2.setFirstName("John");
//       client2.setLastName("thegreat");
//       client2.setHeight(173);
//       client2.setWeight(72);
//       client2.setPhoneNumber("0612399193");
//       
//       allClients.add(client);
//       allClients.add(client2);
    }
    
    
    public void removeClient(int id){
        try{
                Connection con = db.getConnection();
                PreparedStatement ps = con.prepareStatement("delete from Client where id = ?;");
                ps.setInt(1, id);
                ps.execute();
                db.closeConnection(con);
            }catch(SQLException e){
                e.printStackTrace();
            }
    }
    
    
    public void addClient(String date, Client client){
        
        java.sql.Date sql = null;
       
        try{
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date parsed = format.parse(date);
        sql = new java.sql.Date(parsed.getTime());
        }catch(ParseException e){
            
        }
        
        System.out.println(client.getBirthDate());
        
        String query = "insert into Client(voornaam, tussenvoegsel, achternaam, gewicht, lengte, geboortedatum, tel_nr) values (?,?,?,?,?,?,?);";
        try{
            
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getMiddleName());
            ps.setString(3, client.getLastName());
            ps.setDouble(4, client.getWeight());
            ps.setInt(5, client.getHeight());
            ps.setDate(6, sql);
            ps.setString(7, client.getPhoneNumber());
            ps.execute();
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        } 
    }
    
    public void updateClient(String clientdate, Client client){
        
        String query = "update client set voornaam = ?, tussenvoegsel = ?, achternaam = ?, gewicht = ?, lengte = ?, geboortedatum = ?, tel_nr = ? where id = ?";
        java.sql.Date sql = null;
        
        try{
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date parsed = format.parse(clientdate);
        sql = new java.sql.Date(parsed.getTime());
        
        }catch(ParseException e){
            
        }
        
        try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getMiddleName());
            ps.setString(3, client.getLastName());
            ps.setDouble(4, client.getWeight());
            ps.setInt(5, client.getHeight());
            ps.setDate(6, sql);
            ps.setString(7, client.getPhoneNumber());
            ps.setInt(8, client.getClientID());
            ps.execute();
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }  

    }
   
    public List getClient(){
       allClients.clear();
       Date newDate = null;

       
       try{
            Connection con = db.getConnection();
            
            String query = "select * from client;";
            PreparedStatement allUsers = con.prepareStatement(query);
            
            ResultSet rs = allUsers.executeQuery();
                        
            Client client;
            while(rs.next()){
                newDate = rs.getDate("geboortedatum");
                
                client = new Client();
                client.setClientID(rs.getInt("id"));
                client.setFirstName(rs.getString("voornaam"));
                client.setMiddleName(rs.getString("tussenvoegsel"));
                client.setLastName(rs.getString("achternaam"));
                client.setWeight(rs.getDouble("gewicht"));
                client.setHeight(rs.getInt("lengte"));
                client.setBirthDate(newDate);
                client.setPhoneNumber(rs.getString("tel_nr"));
                allClients.add(client);
           }
            
            System.out.println("GOT ALL THE CLIENTS : " + allClients);
            
          db.closeConnection(con);
            return allClients;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List getCoachClients(int id){
       allClients.clear();
       
       Date newDate = null;
       
       try{
            Connection con = db.getConnection();
            
            String query = "select * from client where coach_id=?;";
            PreparedStatement allUsers = con.prepareStatement(query);
            
            allUsers.setInt(1, id);
            ResultSet rs = allUsers.executeQuery();

            System.out.println("ABOUT TO GET CLIENTS THAT ARE FROM COACH : " + id);
                        
            Client client;
            while(rs.next()){
                newDate = rs.getDate("geboortedatum");
                
                client = new Client();
                client.setClientID(rs.getInt("id"));
                client.setFirstName(rs.getString("voornaam"));
                client.setMiddleName(rs.getString("tussenvoegsel"));
                client.setLastName(rs.getString("achternaam"));
                client.setWeight(rs.getDouble("gewicht"));
                client.setHeight(rs.getInt("lengte"));
                client.setBirthDate(newDate);
                client.setPhoneNumber(rs.getString("tel_nr"));
                allClients.add(client);
           }
            
            System.out.println("GOT ALL THE CLIENTS : " + allClients);
            
          db.closeConnection(con);
            return allClients;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public void transferClient(int client_id, int coach_id) {
        System.out.println("INSIDE THE TRANSFER CLIENT : " + client_id + " COACH : " + coach_id);
        
        
        try{
            Connection con = db.getConnection();
            
            String query = "UPDATE client SET coach_id = ? WHERE id = ?;";

            PreparedStatement update_user = con.prepareStatement(query);

            update_user.setInt(1, coach_id);
            update_user.setInt(2, client_id);
            

            update_user.executeUpdate();
            
            System.out.println("I FINISHED AND WORKED @TRANSFER");
            
          db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
            
    
}
