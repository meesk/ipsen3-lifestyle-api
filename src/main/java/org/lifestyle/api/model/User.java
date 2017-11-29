package org.lifestyle.api.model;

//import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @NotEmpty
    @Length(min = 3, max = 100)
    @JsonView(View.Public.class)
    private String userName;
    
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
    @Email
    @JsonView(View.Public.class)
    private String emailAddress;
    
    @NotEmpty
    @Length()
    @JsonView(View.Private.class)
    private String[] roles;
    
    @NotEmpty
    @JsonView(View.Protected.class)
    private String status;
    
    public boolean equals(User user)
    {
        return getEmailAddress().equals(user.getEmailAddress());
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }
}
