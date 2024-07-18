package com.example.DC_Test_Telegin.dto;

import com.example.DC_Test_Telegin.models.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ManufacturerDTO {
    private int id;
    @Size(min = 1, max = 40, message = "Минимальный размер имени 1, максимальный 40 символов")
    @NotEmpty(message = "Поле не должно быть пустым")
    private String name;
    private int yearOfCreation;
    private String country;

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

    public int getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(int yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
