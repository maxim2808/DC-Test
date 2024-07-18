package com.example.DC_Test_Telegin.dto;

import jakarta.validation.Valid;

public class ProductManufacturerDTO {
    @Valid
    ProductDTO product;
    int manufacturerID;

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(int manufacturerID) {
        this.manufacturerID = manufacturerID;
    }
}
