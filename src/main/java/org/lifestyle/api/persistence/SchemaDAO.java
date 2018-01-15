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
import org.lifestyle.api.model.FeedingSchema;
import org.lifestyle.api.model.FeedingSegment;
import org.lifestyle.api.model.Product;
import org.lifestyle.api.model.User;

/**
 *
 * @author DannyW
 */
public class SchemaDAO {
    
    private final Database db;
    
    public SchemaDAO() {
        this.db = new Database();
    }
    
    public FeedingSchema getDetail(User user, int schemaId) {
        List<FeedingSchema> schemas = new ArrayList<FeedingSchema>();
        
        try{
            Connection con = db.getConnection();
            
            PreparedStatement ps = con.prepareStatement("select * from voedingschema v "
                    + "join client c on v.client_id = c.id join voedingschema s on c.id = s.client_id "
                    + "where c.coach_id = ? and s.id = ?;");
            ps.setInt(1, user.getUserId());
            ps.setInt(2, schemaId);
            ResultSet rs = ps.executeQuery();
            
            FeedingSchema schema = null;
            while(rs.next()){
                schema = new FeedingSchema();
                schema.setId(rs.getInt("id"));
                schema.setClientId(rs.getInt("client_id"));
                schema.setCreatedOn(rs.getDate("aangemaakt_op"));
                schemas.add(schema);
            }
            db.closeConnection(con);
            if (schema != null) {
                SegmentDAO segmentDAO = new SegmentDAO();
                schema.setFeedingSegments(segmentDAO.getAll(schema.getId()));
            }
            return schema;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<FeedingSchema> getAll(User user) {
        List<FeedingSchema> schemas = new ArrayList<FeedingSchema>();
        
        try{
            Connection con = db.getConnection();
            
            PreparedStatement ps = con.prepareStatement("select * from voedingschema v "
                    + "join client c on v.client_id = c.id where c.coach_id = ?;");
            ps.setInt(1, user.getUserId());
            ResultSet rs = ps.executeQuery();
            
            FeedingSchema schema;
            while(rs.next()){
                schema = new FeedingSchema();
                schema.setId(rs.getInt("id"));
                schema.setClientId(rs.getInt("client_id"));
                schema.setCreatedOn(rs.getDate("aangemaakt_op"));
                schemas.add(schema);
            }
            db.closeConnection(con);
            return schemas;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public void add(FeedingSchema schema, User user){
        int generatedKey = 0;
        try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO voedingschema (client_id, aangemaakt_op) "
                    + "VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, schema.getClientId());
            ps.setDate(2, schema.getCreatedOn());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        if (generatedKey > 0) {
            SegmentDAO segmentDAO = new SegmentDAO();
            for ( FeedingSegment segment: schema.getFeedingSegments())
                segmentDAO.add(segment, generatedKey);
        }
    }
}
