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
import org.lifestyle.api.model.Knowledge;

/**
 *
 * @author Laurens Jan
 */
@Singleton
public class KnowledgeDAO {

    private final List<Knowledge> knows;
    private final Database db;
    private List<Knowledge> knowledgeList = new ArrayList<Knowledge>();

    @Inject
    public KnowledgeDAO() {
        knows = new ArrayList<>();
        db = new Database();
    }

    public void add(Knowledge knowledge){
        if(!knowledge.getKnowledge().trim().isEmpty()){
            try{
                Connection con = db.getConnection();
                PreparedStatement ps = con.prepareStatement("insert into kennis(tip,toegevoegd_door,bevestigd) values(?,?,?);");
                ps.setString(1, knowledge.getKnowledge().trim());
                ps.setInt(2,knowledge.getAddedBy());
                ps.setBoolean(3, knowledge.getConfirmed());
                ps.execute();
                con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }else{
        }
    }

    public List<Knowledge> getAll() {
//        return knowledgeList;
        knows.clear();
        try{
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from kennis order by id;");
            Knowledge know;
            while(rs.next()){
                know = new Knowledge();
                know.setKnowledge(rs.getString("tip").trim());
                know.setId(rs.getInt("id"));
//                know.setAddedBy(rs.getInt("toegevoegd_door"));
                know.setConfirmed(rs.getBoolean("bevestigd"));
                knows.add(know);
            }
            db.closeConnection(con);
            return knows;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public void update(int id,Knowledge knowledge){ 
        try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("update kennis set tip = ?, bevestigd = ? where id = ?;");
            ps.setString(1,knowledge.getKnowledge());
            ps.setBoolean(2, knowledge.getConfirmed());
            ps.setInt(3,id);
            ps.execute();
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void delete(List<Integer> id){
        for(int x : id){
            try{
                Connection con = db.getConnection();
                PreparedStatement ps = con.prepareStatement("delete from kennis where id = ?;");
                ps.setInt(1, x);
                ps.execute();
                db.closeConnection(con);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
