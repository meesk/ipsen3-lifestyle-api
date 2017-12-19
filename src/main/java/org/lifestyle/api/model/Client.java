
package org.lifestyle.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.Date;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.lifestyle.api.View;

/**
 *
 * @author abdoul
 */
public class Client {
    
    @Length(min = 3, max = 60)
    @JsonView(View.Public.class)
    private String name;
    
    @NotEmpty
    @Length(min = 3, max = 30)
    @JsonView(View.Public.class)
    private String firstName;
    
    @Length(min = 3, max = 10)
    @JsonView(View.Public.class)
    private String middleName;
    
    @NotEmpty   
    @Length(min = 3, max = 30)
    @JsonView(View.Public.class)
    private String lastName;
    
    @Length(min = 10, max = 50)
    @JsonView(View.Public.class)
    private String phoneNumber;
    
    @NotNull
    @JsonView(View.Public.class)
    private double weight;
    
    @NotNull
    @JsonView(View.Public.class)
    private int height;
    
    @JsonView(View.Public.class)
    private Date birthDate;
    
    @NotNull
    @JsonView(View.Protected.class)
    private int clientID;
    
    @JsonView(View.Protected.class)
    private int coachID;
   
    
    // All the setters 
    public void setName(String firstName, String lastName){
        this.name = this.firstName + " " + this.lastName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public void setMiddleName(String middleName){
        this.middleName = middleName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public void setWeight(double weight){
        this.weight = weight;
    }
    
    public void setHeight(int height){
        this.height = height;
    }
    
    public void setBirthDate(Date birthDate){
        this.birthDate = birthDate;
    }
    
    public void setClientID(int clientID){
        this.clientID = clientID;
    }
    
    public void setCoachID(int coachID){
        this.coachID = coachID;
    }
    
    // All the getters
     public String getName()
    {
        return firstName + " " + lastName;
    }
    
    public String getFirstName()
    {
        return firstName;
               
    }
    
    public String getMiddleName()
    {
        return middleName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getPhoneNumber(){
        return phoneNumber;
    }
    
    public double getWeight(){
        return weight;
    }
    
    public int getHeight(){
        return height;
    }
    
    public Date getBirthDate(){
        return birthDate;
    }
    
    public int getClientID(){
        return clientID;
    }
    
    public int getCoachID(){
        return coachID;
    }
}


