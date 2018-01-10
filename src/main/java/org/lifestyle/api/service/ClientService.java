package org.lifestyle.api.service;

import java.util.Collection;
import javax.inject.Singleton;
import org.lifestyle.api.model.Client;
import org.lifestyle.api.model.User;
import org.lifestyle.api.persistence.ClientDAO;

/**
 *
 * @author abdoul
 */
@Singleton
public class ClientService extends BaseService<Client> {
    
    private final ClientDAO dao;
    
    public ClientService(){
        this.dao = new ClientDAO();
    }
    
    public Collection<Client> getAllClients(){
        
        return dao.getClient();
    }
    
    public Collection<Client> getCoachClients(int id){
        
        return dao.getCoachClients(id);
    }
    
    public void addClient(String date, Client client){
        dao.addClient(date, client);
    }
    
    public void getClient(int id){
        dao.getClient();
    }
    
    public void deleteClient(int id){
        dao.removeClient(id);
    }
    
    public void updateClient(String format, Client client){
      dao.updateClient(format, client);
    }
}


