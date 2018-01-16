package org.lifestyle.api.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;
import org.lifestyle.api.model.ProductNutrient;

/**
 *
 * @author Mees Kluivers
 */
@Singleton
public class ProductNutrientDAO 
{
    private final List<ProductNutrient> productNutrients;
    private final Database db;
    
    public ProductNutrientDAO() {
          productNutrients = new ArrayList<>();
          db = new Database();
    }
    
    public List<ProductNutrient> getAll()
    {
        return productNutrients;
    }
    
    public void add(ProductNutrient pn) {
        try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO product_voedingswaarde (voedingswaarde_id, productcode, aantal) "
                    + "VALUES (?,?,?)");
            ps.setInt(1,pn.getNutrientId());
            ps.setInt(2,pn.getProductId());
            ps.setBigDecimal(3, pn.getAmount());
            ps.execute();
            
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void addTemp(ProductNutrient pn) {
        try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO temp_product_voedingswaarde (voedingswaarde_id, productcode, aantal) "
                    + "VALUES (?,?,?)");
            ps.setInt(1,pn.getNutrientId());
            ps.setInt(2,pn.getProductId());
            ps.setBigDecimal(3, pn.getAmount());
            ps.execute();
            
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void update(int id, ProductNutrient pn) {
        try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE product_voedingswaarde SET aantal = ? WHERE productcode = ? AND voedingswaarde_id = ?");
            ps.setBigDecimal(1, pn.getAmount());
            ps.setInt(2,pn.getProductId());
            ps.setInt(3,pn.getNutrientId());
            ps.execute();
            
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void delete(int prodId) {
         try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM product_voedingswaarde WHERE productcode = ?");
            ps.setInt(1, prodId);
            ps.execute();
            
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
