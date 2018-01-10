package org.lifestyle.api.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
    
    public Client get(int id)
    {
        try
        {
            return allClients.get(id);
        }
        catch(IndexOutOfBoundsException exception)
        {
            return null;
        }
    }
    
    public void removeClient(int id){
        Client currentClient;
        for(Client client : allClients){
            if(client.getClientID() == id){
               currentClient = client;
               System.out.println(currentClient.getName() + " is succesvol verwijderd.");
               break;
            }
        }
        
        allClients.remove(id);
        
        int i = 1;
        for(Client clients : allClients){
            System.out.println(clients.getFirstName() + " " + i);
            i++;
        }
    }
    
    public void addClient(Client client){
        allClients.add(client);
        
        int i = 1;
        for(Client clients : allClients){
            System.out.println(clients.getFirstName() + " " + i);
            i++;
        }
    }
    
    public void updateClient(Client client){
        
        String query = "update client set voornaam = ?, tussenvoegsel = ?, achternaam = ?, gewicht = ?, lengte = ?, geboortedatum = ?, tel_nr = ? where id = ?";
       
        try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getMiddleName());
            ps.setString(3, client.getLastName());
            ps.setDouble(4, client.getWeight());
            ps.setInt(5, client.getHeight());
            ps.setString(6, client.getBirthDate());
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
       
       try{
            Connection con = db.getConnection();
            
            String query = "select * from client;";
            PreparedStatement allUsers = con.prepareStatement(query);
            
            ResultSet rs = allUsers.executeQuery();
                        
            Client client;
            while(rs.next()){
                client = new Client();
                client.setClientID(rs.getInt("id"));
                client.setFirstName(rs.getString("voornaam"));
                client.setMiddleName(rs.getString("tussenvoegsel"));
                client.setLastName(rs.getString("achternaam"));
                client.setWeight(rs.getDouble("gewicht"));
                client.setHeight(rs.getInt("lengte"));
                client.setBirthDate(rs.getString("geboortedatum"));
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
       
       try{
            Connection con = db.getConnection();
            
            String query = "select * from client where coach_id=?;";
            PreparedStatement allUsers = con.prepareStatement(query);
            
            allUsers.setInt(1, id);
            ResultSet rs = allUsers.executeQuery();

            System.out.println("ABOUT TO GET CLIENTS THAT ARE FROM COACH : " + id);
                        
            Client client;
            while(rs.next()){
                client = new Client();
                client.setClientID(rs.getInt("id"));
                client.setFirstName(rs.getString("voornaam"));
                client.setMiddleName(rs.getString("tussenvoegsel"));
                client.setLastName(rs.getString("achternaam"));
                client.setWeight(rs.getDouble("gewicht"));
                client.setHeight(rs.getInt("lengte"));
                client.setBirthDate(rs.getString("geboortedatum"));
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
            
    
}
