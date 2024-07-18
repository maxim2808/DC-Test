package com.example.DC_Test_Telegin.dto;

public class ProductManufacturerDTO {
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
