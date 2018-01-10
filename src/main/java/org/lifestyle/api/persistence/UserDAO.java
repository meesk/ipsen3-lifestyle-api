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

    private Database DB;

    public UserDAO() {
        this.DB = new Database();
    }

    public List<User> getAll() {
         
        List<User> users = new ArrayList<User>();
        
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
                user.setRole(User.UserRoles.valueOf(rs.getInt("rol_id")));
                
                users.add(user);
            }
            
            DB.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return users;
    }

    public User getById(int id) {
        Optional<User> result = getAll().stream()
                .filter(user -> user.getUserId() == id)
                .findAny();

        return result.isPresent()
                ? result.get()
                : null;
    }

    public User getByName(String name) {
        Optional<User> result = getAll().stream()
                .filter(user -> user.getName().equals(name))
                .findAny();

        return result.isPresent()
                ? result.get()
                : null;
    }

    public User getByEmailAddress(String emailAddress) {
        Optional<User> result = getAll().stream()
                .filter(user -> user.getEmailAddress().equals(emailAddress))
                .findAny();

        return result.isPresent()
                ? result.get()
                : null;
    }

    public void add(User user) {
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into gebruiker"
                    + " (voornaam, tussenvoegsel, achternaam, wachtwoord, rol_id, email) values"
                    + " (?, ?, ?, ?, ?, ?);");
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getMiddleName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRole().getValue());
            ps.setString(6, user.getEmailAddress());
            ps.executeUpdate();
            
            DB.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, User user) {
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("update gebruiker set "
                    + "voornaam = ?, tussenvoegsel = ?, achternaam = ? "
                    + "wachtwoord = ?, rol_id = ?, email = ? "
                    + "where id = ?;");
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getMiddleName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRole().getValue());
            ps.setString(6, user.getEmailAddress());
            ps.setInt(7, id);
            ps.executeUpdate();
            
            DB.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from gebruiker"
                    + " where id = ?;");
            ps.setInt(1, id);
            ps.executeUpdate();
            
            DB.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
