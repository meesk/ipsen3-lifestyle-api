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
import org.lifestyle.api.model.Product;
import org.lifestyle.api.persistence.ProductDAO;

/**
 *
 * @author meesk
 */
@Singleton
public class ProductService {
    
        
    private final ProductDAO dao;
    @Inject
    public ProductService(ProductDAO dao){
        this.dao=dao;
    }
    
    public void insertBulk(Product[] product){
        dao.addBulk(product);
    }
    
    public void insert(Product product){
        dao.add(product);
    }
    
    public Collection<Product> getAll(){
        return dao.getAll();
    }
    
    public void update(int id, Product product){
        dao.update(id,product);
    }
    
    public void delete(List<Integer> id){
        dao.delete(id);
    }
    
}
