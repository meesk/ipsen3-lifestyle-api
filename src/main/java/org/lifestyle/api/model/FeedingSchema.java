/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import java.sql.Date;
import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;
import org.lifestyle.api.View;

/**
 *
 * @author DannyW
 */
public class FeedingSchema {

    @NotEmpty
    @JsonView(View.Public.class)
    private int id;
    
    @NotEmpty
    @JsonView(View.Public.class)
    private int clientId;
    
    @NotEmpty
    @JsonView(View.Public.class)
    private List<FeedingSegment> feedingSegments;
    
    @NotEmpty
    @JsonView(View.Public.class)
    private Date createdOn;
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the clientId
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the createdOn
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * @param createdOn the createdOn to set
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * @return the feedingSegments
     */
    public List<FeedingSegment> getFeedingSegments() {
        return feedingSegments;
    }

    /**
     * @param feedingSegments the feedingSegments to set
     */
    public void setFeedingSegments(List<FeedingSegment> feedingSegments) {
        this.feedingSegments = feedingSegments;
    }
}
