package com.example.DC_Test_Telegin.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;
    @Column(name = "product_name")
    @Size(min = 1, max = 50, message = "Минимальный размер имени 1, максимальный 50 символов")
    @NotEmpty
    private String productName;
    @Column(name = "price")
    @DecimalMin(value = "0.01", message = "Минимальное значение должно быть минимум 0,01")
    private Double price;
    @Column(name = "quantity")
    @Min(value = 0, message = "Минимальное количество 0")
    private int quantity;
    @Column(name = "weight")
    @Min(value = 0, message = "Минимальный вес 0")
    private double weight;
    @Column(name = "year_of_release")
    @Min(value = 1950, message = "Минимальный год 1950")
    @Max(value = 2024, message = "Максмимальный год 2024")
    private int yearOfRelease;
    @Column(name = "registration_date")
    private String registrationDate;
    @ManyToOne()
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "manufacturer_id")
    private Manufacturer manufacturer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
