package org.lifestyle.api.persistence;

import java.sql.Connection;
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

    private final List<User> users;
    private Database DB;

    public UserDAO() {
        
        this.DB = new Database();
        users = new ArrayList<>();
        fillUsers();
        
//        User user1 = new User();
//        user1.setUserId(123);
//        user1.setFirstName("First");
//        user1.setMiddleName("ghjhg");
//        user1.setLastName("User");
//        user1.setStatus("ACTIVE");
//        user1.setEmailAddress("first@user.com");
//        user1.setPassword("first");
//        user1.setRole("COACH");
//
//        User user2 = new User();
//        user2.setUserId(234);
//        user2.setFirstName("Second");
//        user2.setMiddleName("uhu");
//        user2.setLastName("User");
//        user2.setStatus("ACTIVE");
//        user2.setEmailAddress("admin@user.com");
//        user2.setPassword("admin");
//        user2.setRole("ADMIN");
//
//        User user3 = new User();
//        user3.setUserId(345);
//        user3.setFirstName("Third");
//        user3.setMiddleName("ghjhg");
//        user3.setLastName("User");
//        user3.setStatus("NOT_CONFIRMED");
//        user3.setEmailAddress("third@user.com");
//        user3.setPassword("third");
//        user3.setRole("COACH");
//
//        User user4 = new User();
//        user4.setUserId(456);
//        user4.setFirstName("Fourth");
//        user4.setMiddleName("ghjhg");
//        user4.setLastName("User");
//        user4.setStatus("ACTIVE");
//        user4.setEmailAddress("fourth@user.com");
//        user4.setPassword("fourth");
//        user4.setRole("COACH");
//
//        User user5 = new User();
//        user5.setUserId(567);
//        user5.setFirstName("fifth");
//        user5.setMiddleName("ghjhg");
//        user5.setLastName("User");
//        user5.setStatus("INACTIVE");
//        user5.setEmailAddress("fifth@user.com");
//        user5.setPassword("fifth");
//        user5.setRole("COACH");
//
        
//        users.add(user1);
//        users.add(user2);
//        users.add(user3);
//        users.add(user4);
//        users.add(user5);
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
                
                if(rs.getInt("rol_id") == 1) 
                    user.setRole(User.UserRoles.COACH.toString());
                else
                    user.setRole(User.UserRoles.ADMIN.toString());
                
                users.add(user);
            }
            
            DB.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
