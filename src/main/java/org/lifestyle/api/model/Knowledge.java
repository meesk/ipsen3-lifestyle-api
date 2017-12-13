/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import javax.validation.constraints.NotNull;
import org.lifestyle.api.View;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Laurens Jan
 */
public class Knowledge {
    
    @NotEmpty
    @Length(max = 100)
    @JsonView(View.Public.class)
    private String knowledge;
    
    @JsonView(View.Private.class)
    private int id;
    
    @JsonView(View.Private.class)
    private boolean confirmed;
    
    @NotNull
    @JsonView(View.Public.class)
    private int addedBy;
    
    public void setKnowledge(String knowledge){
        this.knowledge=knowledge;
    }
    
    public String getKnowledge(){
        return this.knowledge;
    }
    
    public void setId(int id){
        this.id=id;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setAddedBy(int addedBy){
        this.addedBy = addedBy;
    }
    
    public int getAddedBy(){
        return addedBy;
    }
    
    public void setConfirmed(boolean confirmed){
        this.confirmed = confirmed;
    }
    
    public boolean getConfirmed(){
        return confirmed;
    }
}
