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
    public enum UserStatus {
        ACTIVE, INACTIVE, NOT_CONFIRMED;
    }
    
    public enum UserRoles {
        COACH, ADMIN
    }
    
    @JsonView(View.Public.class)
    private int userId;

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
    private String password;
    
    @NotEmpty
    @Email
    @JsonView(View.Public.class)
    private String emailAddress;
    
    @JsonView(View.Public.class)
    private UserRoles role;
    
    @JsonView(View.Public.class)
    private UserStatus status;
    
    @JsonView(View.Internal.class)
    private byte[] salt;
    
    @JsonView(View.Internal.class)
    private byte[] hash;
    
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
    public UserStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }
    
    public void setStatus(String status) {
        this.status = UserStatus.valueOf(status.toUpperCase());
    }
    
    public boolean isRole(String role)
    {
        return role.equals(this.getRole().name());
    }

    /**
     * @return the roles
     */
    public UserRoles getRole() {
        return role;
    }

    /**
     * @param roles the roles to set
     */
    public void setRole(UserRoles role) {
        this.role = role;
    }
    
    public void setRole(String role) {
        this.role = UserRoles.valueOf(role.toUpperCase());
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
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
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
