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
import org.lifestyle.api.model.Nutrient;

/**
 *
 * @author meesk
 */
@Singleton
public class NutrientDAO {
    
    
    private final List<Nutrient> nutrients;
//    private final Database db;

    @Inject
    public NutrientDAO() {
        nutrients = new ArrayList<>();
        
        Nutrient nutrient1 = new Nutrient();
        nutrient1.setNutrientId(1);
        nutrient1.setNutrientName("Koolhydraten");
        nutrient1.setNutrientMeasurement("g");
        nutrients.add(nutrient1);
        
        Nutrient nutrient2 = new Nutrient();
        nutrient2.setNutrientId(2);
        nutrient2.setNutrientName("Vezels");
        nutrient2.setNutrientMeasurement("g");
        nutrients.add(nutrient2);
        
//        db = new Database();
    }

    public void addBulk(Nutrient[] nutrient) {
        for (Nutrient x : nutrient) {
            add(x);
        }
    }

    public void add(Nutrient nutrient){
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
            nutrients.add(nutrient);
    }

    public List<Nutrient> getAll() {
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
          return nutrients;
    }
    
    public void update(int id, Nutrient nutrient){ 
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
