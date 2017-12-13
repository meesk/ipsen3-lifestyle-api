/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.persistence;

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
//    private final Database db;

    @Inject
    public ProductDAO() {
        products = new ArrayList<>();
        
        Product product1 = new Product();
        product1.setProductId(123);
        product1.setProductName("Test product");
        product1.setProductDescription("Product description");
        product1.setManufacturerName("Kellog's");
        product1.setAmount(100);
        product1.setIsAdded(true);
        product1.setIsConfirmed(false);
        products.add(product1);
        
        Product product2 = new Product();
        product2.setProductId(123);
        product2.setProductName("Test product2");
        product2.setProductDescription("Product description2");
        product2.setManufacturerName("lala");
        product2.setAmount(200);
        product2.setIsAdded(true);
        product2.setIsConfirmed(true);
        products.add(product2);
        
//        db = new Database();
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
//        products.clear();
//        try{
//            Connection con = db.getConnection();
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery("select * from product order by id");
//            Product product;
//            while(rs.next()){
//                product = new Product();
////                know.setKnowledge(rs.getString("knowledge"));
////                know.setId(rs.getInt("id"));
//                products.add(product);
//            }
//            db.closeConnection(con);
//            return products;
//        }catch(SQLException e){
//            e.printStackTrace();
//            return null;
//        }
          return products;
    }
    
    public void update(int id, Product product){ 
//        try{
//            Connection con = db.getConnection();
//            PreparedStatement ps = con.prepareStatement("update product set product = ? where id = ?");
//            ps.setString(1,product.getProduct());
//            ps.setInt(2,id);
//            ps.execute();
//            db.closeConnection(con);
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
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
