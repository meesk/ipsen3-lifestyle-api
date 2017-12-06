package org.lifestyle.api.model;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import java.security.Principal;
import org.lifestyle.api.View;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

///**
// * Meer informatie over validatie:
// *  http://hibernate.org/validator/
// * 
// * @author Peter van Vliet
// */
public class User implements Principal
{
    @Length(min = 1, max = 100)
    @JsonView(View.Public.class)
    private int userId;
    
//    @NotEmpty
//    @Length(min = 3, max = 100)
//    @JsonView(View.Public.class)
//    private String userName;
    
    @NotEmpty
    @Length(min = 2, max = 100)
    @JsonView(View.Public.class)
    private String firstName;
    
    @Length(min = 0, max = 100)
    @JsonView(View.Public.class)
    private String middleName;
    
    @NotEmpty
    @Length(min = 2, max = 100)
    @JsonView(View.Public.class)
    private String lastName;
    
    @NotEmpty
    @Length(min = 1, max = 40)
    @JsonView(View.Internal.class)
    private char[] password;
    
    @NotEmpty
    @Length(min = 1, max = 40)
    @JsonView(View.Internal.class)
    private byte[] salt;
    
    @Length(min = 1, max = 40)
    @JsonView(View.Internal.class)
    private byte[] hash;
    
    @NotEmpty
    @Email
    @JsonView(View.Public.class)
    private String emailAddress;
    
    @NotEmpty
    @Length()
    @JsonView(View.Public.class)
    private String[] roles;
    
    @NotEmpty
    @JsonView(View.Protected.class)
    private String status;
    
    public boolean equals(User user)
    {
        return getEmailAddress().equals(user.getEmailAddress());
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    public boolean hasRole(String roleName)
    {
        if (roles != null)
        {
            for(String role : roles)
            {
                if(roleName.equals(role))
                {
                    return true;
                }
            }
        }
        
        return false;
    }

    /**
     * @return the roles
     */
    public String[] getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    @Override
    @JsonIgnore
    public String getName() {
        return this.firstName + this.lastName;
        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the password
     */
    public char[] getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(char[] password) {
        this.password = password;
    }

    /**
     * @return the salt
     */
    public byte[] getSalt() {
        return salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    /**
     * @return the hash
     */
    public byte[] getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(byte[] hash) {
        this.hash = hash;
    }
}
