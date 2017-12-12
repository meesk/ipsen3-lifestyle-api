package org.lifestyle.api.service;

import java.util.Collection;
import javax.inject.Singleton;
import org.lifestyle.api.model.Client;
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
    
    public void addClient(Client client){
        dao.addClient(client);
    }
    
    public void getClient(int id){
        dao.get(id);
    }
    
    public void deleteClient(int id){
        dao.removeClient(id);
    }
    
    public void updateClient(int id, Client client){
      dao.updateClient(id, client);
    }
}


