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
import org.lifestyle.api.model.ProductNutrient;

/**
 *
 * @author meesk
 */
@Singleton
public class ProductDAO {
    
    
    private final List<Product> products;
    private final Database db;
    private final ProductNutrientDAO pnDAO;

    @Inject
    public ProductDAO() {
        products = new ArrayList<>();
        db = new Database();
        pnDAO = new ProductNutrientDAO();
    }

    public void addBulk(Product[] knowledge) {
        for (Product x : knowledge) {
            add(x);
        }
    }

    public void add(Product product){
        
        int key = 0;
        try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO product (naam, fabrikantnaam, commentaar, hoeveelheid, meeteenheid, is_bevestigd, is_toegevoegd) "
                    + "VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,product.getProductName());
            ps.setString(2,product.getManufacturerName());
            ps.setString(3, product.getComments());
            ps.setInt(4,product.getAmount());
            ps.setString(5, product.getMeasurement());
            ps.setBoolean(6, product.getIsConfirmed());
            ps.setBoolean(7, product.getIsAdded());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()) {
                key = rs.getInt(1);
            }
            System.out.println(key);
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
            for(ProductNutrient pn : product.getProductNutrients()){
                pn.setProductId(key);
                pnDAO.add(pn);
            }
            products.add(product);
    }

    public List<Product> getAll() {
        products.clear();
        try{
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from product order by is_toegevoegd DESC, is_bevestigd ASC limit 100");
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
            PreparedStatement ps = con.prepareStatement("UPDATE product SET naam = ?,"
                    + " fabrikantnaam = ?,"
                    + " hoeveelheid = ?,"
                    + " meeteenheid = ?,"
                    + " is_bevestigd = ?"
                    + " WHERE productcode = ?");
            ps.setString(1,product.getProductName());
            ps.setString(2,product.getManufacturerName());
            ps.setInt(3,product.getAmount());
            ps.setString(4, product.getMeasurement());
            ps.setBoolean(5, product.getIsConfirmed());
            ps.setInt(6,id);
            ps.execute();
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void delete(int id){
            try{
                Connection con = db.getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM product WHERE productcode = ?");
                ps.setInt(1, id);
                ps.execute();
                System.out.println("HELLO " + id);
                db.closeConnection(con);
            }catch(SQLException e){
                e.printStackTrace();
            }
    }
    
}
