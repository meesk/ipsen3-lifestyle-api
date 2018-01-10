package org.lifestyle.api.model;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.lifestyle.api.View;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

///**
// * Meer informatie over validatie:
// *  http://hibernate.org/validator/
// * 
// * @author Peter van Vliet
// */
public class Product {
    
    public Product(){
        productNutrients = new ArrayList<>();
    }
    
    @JsonView(View.Public.class)
    private Integer productId;
    
    @NotEmpty
    @Length(min = 2, max = 100)
    @JsonView(View.Public.class)
    private String productName;
    
    
    @JsonView(View.Public.class)
    private String manufacturerName;
    
    @NotNull
    @JsonView(View.Public.class)
    private Integer amount;
    
    @NotEmpty
    @Length(min = 1, max = 40)
    @JsonView(View.Public.class)
    private String measurement;
    
    @Length(min = 1, max = 40)
    @JsonView(View.Public.class)
    private String comments;
    
    @NotNull
    @JsonView(View.Public.class)
    private Boolean isAdded;
    
    @NotNull
    @JsonView(View.Public.class)
    private Boolean isConfirmed;
    
    @JsonView(View.Public.class)
    private List<ProductNutrient> productNutrients;

    public List<ProductNutrient> getProductNutrients() {
        return productNutrients;
    }

    public void setProductNutrients(List<ProductNutrient> productNutrients) {
        this.productNutrients = productNutrients;
    }
    
    public void addProductNutrient(ProductNutrient pn){
        this.productNutrients.add(pn);
    }
    
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getIsAdded() {
        return isAdded;
    }

    public void setIsAdded(Boolean isAdded) {
        this.isAdded = isAdded;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }
    
}
