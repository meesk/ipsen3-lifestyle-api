/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.lifestyle.api.View;

/**
 *
 * @author meesk
 */
public class Nutrient {
    
    @JsonView(View.Public.class)
    private Integer nutrientId;
    
    @NotEmpty
    @Length(min = 2, max = 100)
    @JsonView(View.Public.class)
    private String nutrientName;
    
    @NotEmpty
    @Length(min = 0, max = 100)
    @JsonView(View.Public.class)
    private String nutrientMeasurement;

    public Integer getNutrientId() {
        return nutrientId;
    }

    public void setNutrientId(Integer nutrientId) {
        this.nutrientId = nutrientId;
    }

    public String getNutrientName() {
        return nutrientName;
    }

    public void setNutrientName(String nutrientName) {
        this.nutrientName = nutrientName;
    }

    public String getNutrientMeasurement() {
        return nutrientMeasurement;
    }

    public void setNutrientMeasurement(String nutrientMeasurement) {
        this.nutrientMeasurement = nutrientMeasurement;
    }
    
}
