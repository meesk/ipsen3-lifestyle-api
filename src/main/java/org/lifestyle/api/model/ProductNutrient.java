/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import java.math.BigDecimal;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.lifestyle.api.View;

/**
 *
 * @author meesk
 */
public class ProductNutrient {
    
    @JsonView(View.Public.class)
    private Integer nutrientId;
    
    @NotEmpty
    @Length(min = 2, max = 100)
    @JsonView(View.Public.class)
    private Integer productId;
    
    @NotEmpty
    @Length(min = 0, max = 100)
    @JsonView(View.Public.class)
    private BigDecimal amount;

    public Integer getNutrientId() {
        return nutrientId;
    }

    public void setNutrientId(Integer nutrientId) {
        this.nutrientId = nutrientId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
}
