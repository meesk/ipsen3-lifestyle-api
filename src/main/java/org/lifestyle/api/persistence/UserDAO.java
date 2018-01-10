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
import org.lifestyle.api.model.User;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
public class UserDAO {

    private final List<User> users, transferOptions;
    private Database DB;

    public UserDAO() {
        
        this.DB = new Database();
        users = new ArrayList<>();
        transferOptions = new ArrayList<>();
        fillUsers();
        
    }

    public List<User> getAll() {
        fillUsers();
        return users;
    }

    public void fillUsers() {
        
        users.clear();
        
        try {
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from gebruiker;");
            
            while (rs.next()) {
                User user = new User();
                
                user.setUserId(rs.getInt("id"));
                user.setFirstName(rs.getString("voornaam"));
                user.setMiddleName(rs.getString("tussenvoegsel"));
                user.setLastName(rs.getString("achternaam"));
                
                user.setEmailAddress(rs.getString("email"));
                user.setPassword(rs.getString("wachtwoord"));
                
                if(rs.getInt("rol_id") == 1) {
                    user.setRole(User.UserRoles.COACH.toString());
                } else {
                    user.setRole(User.UserRoles.ADMIN.toString());
                }
                
                users.add(user);
            }
            
            DB.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List possibleTransferOptions(int id) {
        
        transferOptions.clear();
        
        System.out.println("INSIDE THE TRANS DAO : " + id);
        
        try {
            Connection con = DB.getConnection();
            
            String query = "SELECT * FROM gebruiker WHERE rol_id = 1 AND id != ?";
            PreparedStatement getTransferOptions = con.prepareStatement(query);
            
            getTransferOptions.setInt(1, id);
            ResultSet rs = getTransferOptions.executeQuery();

            
            while (rs.next()) {
                User user = new User();
                
                user.setUserId(rs.getInt("id"));
                user.setFirstName(rs.getString("voornaam"));
                user.setMiddleName(rs.getString("tussenvoegsel"));
                user.setLastName(rs.getString("achternaam"));
                
                user.setEmailAddress(rs.getString("email"));
                user.setPassword(rs.getString("wachtwoord"));
                
                if(rs.getInt("rol_id") == 1) {
                    user.setRole(User.UserRoles.COACH.toString());
                } else {
                    user.setRole(User.UserRoles.ADMIN.toString());
                }
                
                transferOptions.add(user);
            }
            
            System.out.println("GOT ALL USERS THINGIES");
            
            DB.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return transferOptions;
    }

    public User getById(int id) {
        Optional<User> result = users.stream()
                .filter(user -> user.getUserId() == id)
                .findAny();

        return result.isPresent()
                ? result.get()
                : null;
    }

    public User getByName(String name) {
        Optional<User> result = users.stream()
                .filter(user -> user.getName().equals(name))
                .findAny();

        return result.isPresent()
                ? result.get()
                : null;
    }

    public User getByEmailAddress(String emailAddress) {
        Optional<User> result = users.stream()
                .filter(user -> user.getEmailAddress().equals(emailAddress))
                .findAny();

        return result.isPresent()
                ? result.get()
                : null;
    }

    public void add(User user) {
        users.add(user);
    }

    public void update(int id, User user) {
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId() == id) {
                index = i;
            }
        }
        if (index != -1) {
            users.set(index, user);
        }
    }

    public void delete(int id) {
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId() == id) {
                index = i;
            }
        }
        if (index != -1) {
            users.remove(index);
        }
    }
}
