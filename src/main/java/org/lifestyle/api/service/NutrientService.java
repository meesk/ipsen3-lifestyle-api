/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.service;

import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.lifestyle.api.model.Nutrient;
import org.lifestyle.api.persistence.NutrientDAO;

/**
 *
 * @author meesk
 */
@Singleton
public class NutrientService {
    
        
    private final NutrientDAO dao;
    @Inject
    public NutrientService(NutrientDAO dao){
        this.dao=dao;
    }
    
    public void renameTable(){
        dao.renameTable();

    }
    public void insertBulk(Nutrient[] product){
        dao.addBulk(product);
    }
    
    public void insert(Nutrient product){
        dao.add(product);
    }
    
    public Collection<Nutrient> getAll(){
        return dao.getAll();
    }
    
    public void update(int id, Nutrient product){
        dao.update(id,product);
    }
    
    public void delete(List<Integer> id){
        dao.delete(id);
    }
    
    public void newDB(){
        dao.createNewDb();
    }
}
