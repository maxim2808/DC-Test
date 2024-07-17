package com.example.DC_Test_Telegin.dto;

import jakarta.validation.constraints.*;

public class ProductDTO {

    private int id;
    @Size(min = 1, max = 50, message = "Минимальный размер имени 1, максимальный 50 символов")
    @NotEmpty
    private String name;
    @DecimalMin(value = "0.01", message = "Минимальное значение должно быть минимум 0,01")
    private Double price;
    @Min(value = 0, message = "Минимальное количество 0")
    private int quantity;
    @Min(value = 0, message = "Минимальный вес 0")
    private double weight;
    @Min(value = 1950, message = "Минимальный год 1950")
    @Max(value = 2024, message = "Максмимальный год 2024")
    private int yearOfRelease;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }
}
