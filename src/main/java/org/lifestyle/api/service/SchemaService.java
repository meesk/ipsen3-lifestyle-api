/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.service;

import java.util.Collection;
import org.lifestyle.api.model.FeedingSchema;
import org.lifestyle.api.model.User;
import org.lifestyle.api.persistence.SchemaDAO;

/**
 *
 * @author DannyW
 */
public class SchemaService extends BaseService<FeedingSchema> {
    
    private final SchemaDAO dao;
    
    public SchemaService(){
        this.dao = new SchemaDAO();
    }
    
    public Collection<FeedingSchema> getAll(User user){
        return dao.getAll(user);
    }
    
    public FeedingSchema getDetail(User user, int id){
        return dao.getDetail(user, id);
    }
}
