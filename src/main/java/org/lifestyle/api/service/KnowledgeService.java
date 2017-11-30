/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.service;

import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.lifestyle.api.model.Knowledge;
import org.lifestyle.api.persistence.KnowledgeDAO;

/**
 *
 * @author Laurens Jan
 */
@Singleton
public class KnowledgeService {
    
    private final KnowledgeDAO dao;
    @Inject
    public KnowledgeService(KnowledgeDAO dao){
        this.dao=dao;
    }
    
    public void insertBulk(Knowledge[] knowledge){
        dao.addBulk(knowledge);
    }
    
    public void insert(Knowledge knowledge){
        dao.add(knowledge);
    }
    
    public Collection<Knowledge> getAll(){
        return dao.getAll();
    }
    
    public void update(int id, Knowledge knowledge){
        dao.update(id,knowledge);
    }
}
