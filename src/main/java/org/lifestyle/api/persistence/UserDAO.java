package org.lifestyle.api.persistence;

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
public class UserDAO
{
    private final List<User> users;
    
    public UserDAO()
    {
        User user1 = new User();
        user1.setFirstName("First");
        user1.setMiddleName("");
        user1.setLastName("User");
        user1.setUserName("First");
        user1.setStatus("CONFIRMED");
        user1.setEmailAddress("first@user.com");
        user1.setRoles(new String[] { "GUEST", "ADMIN" });
        
        User user2 = new User();
        user2.setFirstName("Second");
        user2.setMiddleName("uhu");
        user2.setLastName("User");
        user2.setUserName("Second");
        user2.setStatus("TO_BE_CONFIRMED");
        user2.setEmailAddress("second@user.com");
        user2.setRoles(new String[] { "GUEST" });
        
        users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
    }
    
    public List<User> getAll()
    {
        return users;
    }
    
    public User getByName(String name)
    {
        Optional<User> result = users.stream()
            .filter(user -> user.getName().equals(name))
            .findAny();
        
        return result.isPresent()
            ? result.get()
            : null;
    }
    
    public User getByEmailAddress(String emailAddress)
    {
        Optional<User> result = users.stream()
            .filter(user -> user.getEmailAddress().equals(emailAddress))
            .findAny();
        
        return result.isPresent()
            ? result.get()
            : null;
    }
    
    public void add(User user)
    {
        users.add(user);
    }
    
    public void update(int id, User user)
    {
        users.set(id, user);
    }
    
    public void delete(int id)
    {
        users.remove(id);
    }
}
