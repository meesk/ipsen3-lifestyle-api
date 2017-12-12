package org.lifestyle.api.persistence;

import java.util.ArrayList;
import java.util.List;
import org.lifestyle.api.model.Client;


public class ClientDAO {
    
    public ArrayList<Client> allClients;
    
    public ClientDAO(){
       allClients = new ArrayList();
       
       Client client = new Client();
       client.setFirstName("Barry");
       client.setLastName("Pieters");
       client.setHeight(185);
       client.setWeight(80);
       client.setPhoneNumber("0618481481");
       
       Client client2 = new Client();
       client2.setFirstName("John");
       client2.setLastName("thegreat");
       client2.setHeight(173);
       client2.setWeight(72);
       client2.setPhoneNumber("0612399193");
       
       allClients.add(client);
       allClients.add(client2);
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
    
    public void updateClient(int id, Client client){
        Client currentClient;
        for(Client clients : allClients){
            if(clients.getClientID() == id){
                currentClient = clients;
                System.out.println(currentClient.getLastName() + " de oude client");
                allClients.remove(currentClient);
                currentClient.setFirstName(client.getFirstName());
                currentClient.setMiddleName(client.getMiddleName());
                currentClient.setLastName(client.getLastName());
                currentClient.setPhoneNumber(client.getPhoneNumber());
                currentClient.setWeight(client.getWeight());
                currentClient.setHeight(client.getHeight());
                currentClient.setBirthDate(client.getBirthDate());
                currentClient.setClientID(id);
                allClients.add(currentClient);
                System.out.println(currentClient.getFirstName() + " de nieuwe client");
                break;     
            }
        }
        
       

    }
   
    public List getClient(){
        return allClients;
    }
            
    
}
