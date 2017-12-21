/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.lifestyle.api.model.Product;

/**
 *
 * @author meesk
 */
@Singleton
public class ProductDAO {
    
    
    private final List<Product> products;
    private final Database db;

    @Inject
    public ProductDAO() {
        products = new ArrayList<>();
        
        Product product1 = new Product();
        product1.setProductId(124);
        product1.setProductName("Test product");
//        product1.setProductDescription("Product description");
        product1.setManufacturerName("Kellog's");
        product1.setMeasurement("mg");
        product1.setAmount(100);
        product1.setIsAdded(true);
        product1.setIsConfirmed(false);
        products.add(product1);
        
        Product product2 = new Product();
        product2.setProductId(123);
        product2.setProductName("Test product2");
//        product2.setProductDescription("Product description2");
        product2.setManufacturerName("lala");
        product2.setMeasurement("g");
        product2.setAmount(200);
        product2.setIsAdded(true);
        product2.setIsConfirmed(true);
        products.add(product2);
        
        db = new Database();
    }

    public void addBulk(Product[] knowledge) {
        for (Product x : knowledge) {
            add(x);
        }
    }

    public void add(Product product){
//        if(!product.getProduct().trim().isEmpty()){
//            try{
//                Connection con = db.getConnection();
//                PreparedStatement ps = con.prepareStatement("insert into knowledge(knowledge) values(?)");
//                ps.setString(1, product.getProduct());
//                ps.execute();
//                con.close();
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//        }else{
//        }
            products.add(product);
    }

    public List<Product> getAll() {
        products.clear();
        try{
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from product order by is_toegevoegd DESC limit 100");
            Product product;
            while(rs.next()){
                product = new Product();
                product.setProductId(rs.getInt("productcode"));
                product.setProductName(rs.getString("naam"));
                product.setComments(rs.getString("commentaar"));
                product.setManufacturerName(rs.getString("fabrikantnaam"));
                product.setAmount(rs.getInt("hoeveelheid"));
                product.setMeasurement(rs.getString("meeteenheid"));
                product.setIsAdded(rs.getBoolean("is_toegevoegd"));
                product.setIsConfirmed(rs.getBoolean("is_bevestigd"));
                products.add(product);
            }
            db.closeConnection(con);
            return products;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public void update(int id, Product product){ 
        try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE product set product = ? where id = ?");
            ps.setString(1,product.getProductName());
            ps.setString(1,product.getManufacturerName());
            ps.setInt(1,product.getAmount());
            ps.setBoolean(1, product.getIsConfirmed());
            ps.setInt(2,id);
            ps.execute();
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println("PRODUCTID: " + id);
        System.out.println("PRODUCT: " + product);
    }
    
    public void delete(List<Integer> id){
//        for(int x : id){
//            try{
//                Connection con = db.getConnection();
//                PreparedStatement ps = con.prepareStatement("delete from product where id = ?");
//                ps.setInt(1, x);
//                ps.execute();
//                db.closeConnection(con);
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//        }
    }
    
}
