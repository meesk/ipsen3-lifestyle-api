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
import java.util.ArrayList;
import java.util.List;
import org.lifestyle.api.model.FeedingSegment;
import org.lifestyle.api.model.Product;

/**
 *
 * @author DannyW
 */
public class SegmentDAO {
    
    private final Database db;
    
    public SegmentDAO() {
        this.db = new Database();
    }
    
    public List<FeedingSegment> getAll(int schemaId) {
        List<FeedingSegment> segments = new ArrayList<FeedingSegment>();
        
        try{
            Connection con = db.getConnection();
            
            PreparedStatement ps = con.prepareStatement("select * from voedingsegment "
                    + "where voedingschema_id = ?;");
            ps.setInt(1, schemaId);
            ResultSet rs = ps.executeQuery();
            
            FeedingSegment segment;
            while(rs.next()){
                segment = new FeedingSegment();
                segment.setId(rs.getInt("id"));
                segment.setFeedingScheduleId(rs.getInt("voedingschema_id"));
                segment.setFeedingTime(rs.getString("voeding_tijd"));
                segment.setFeedingDay(rs.getString("voeding_dag"));
                segment.setProductCode(rs.getInt("productcode"));
                segment.setQuantity(rs.getInt("hoeveelheid"));
                ProductDAO pDAO = new ProductDAO();
                Product p = pDAO.getWithNutrients(segment.getProductCode());
                segment.setProduct(p);
                segments.add(segment);
            }
            db.closeConnection(con);
            return segments;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public void add(FeedingSegment segment, int generatedKey) {
        try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO voedingsegment (productcode, voeding_tijd, voeding_dag, voedingschema_id, hoeveelheid) "
                    + "VALUES (?,?,?,?,?)");
            ps.setInt(1, segment.getProductCode());
            ps.setString(2, segment.getFeedingTime());
            ps.setString(3, segment.getFeedingDay());
            ps.setInt(4, generatedKey);
            ps.setInt(5, segment.getQuantity());
            ps.execute();

            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void remove(int schemaId) {
        try{
            Connection con = db.getConnection();
            System.out.println("ID SCHEMA: " + schemaId);
            PreparedStatement ps = con.prepareStatement("DELETE FROM voedingsegment WHERE voedingschema_id = ?");
            ps.setInt(1, schemaId);
            ps.execute();
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
