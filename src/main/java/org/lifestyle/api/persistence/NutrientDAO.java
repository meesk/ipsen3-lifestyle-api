/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private final Database db;

    @Inject
    public NutrientDAO() {
        nutrients = new ArrayList<>();
        db = new Database();
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
        nutrients.clear();
        try{
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from voedingswaarde order by id");
            Nutrient nutrient;
            while(rs.next()){
                nutrient = new Nutrient();
                nutrient.setNutrientId(rs.getInt("id"));
                nutrient.setNutrientMeasurement(rs.getString("eenheid"));
                nutrient.setNutrientName(rs.getString("naam"));
                nutrients.add(nutrient);
            }
            db.closeConnection(con);
            return nutrients;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
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
