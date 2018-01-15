/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import java.sql.Date;
import org.hibernate.validator.constraints.NotEmpty;
import org.lifestyle.api.View;

/**
 *
 * @author DannyW
 */
public class FeedingSegment {

    @JsonView(View.Public.class)
    private int id;
    
    @JsonView(View.Public.class)
    private int feedingScheduleId;
    
    @JsonView(View.Public.class)
    private int productCode;
    
    @JsonView(View.Public.class)
    private Product product;
    
    @JsonView(View.Public.class)
    private String feedingTime;
    
    @JsonView(View.Public.class)
    private String feedingDay;
    
    @JsonView(View.Public.class)
    private int quantity;
    
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
     * @return the feedingScheduleId
     */
    public int getFeedingScheduleId() {
        return feedingScheduleId;
    }

    /**
     * @param feedingScheduleId the feedingScheduleId to set
     */
    public void setFeedingScheduleId(int feedingScheduleId) {
        this.feedingScheduleId = feedingScheduleId;
    }

    /**
     * @return the productCode
     */
    public int getProductCode() {
        return productCode;
    }

    /**
     * @param productCode the productCode to set
     */
    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the feedingTime
     */
    public String getFeedingTime() {
        return feedingTime;
    }

    /**
     * @param feedingTime the feedingTime to set
     */
    public void setFeedingTime(String feedingTime) {
        this.feedingTime = feedingTime;
    }

    /**
     * @return the feedingDay
     */
    public String getFeedingDay() {
        return feedingDay;
    }

    /**
     * @param feedingDay the feedingDay to set
     */
    public void setFeedingDay(String feedingDay) {
        this.feedingDay = feedingDay;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }
}
