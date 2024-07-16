package com.example.DC_Test_Telegin.dto;

import com.example.DC_Test_Telegin.models.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ManufacturerDTO {


    @Size(min = 1, max = 40, message = "Минимальный размер имени 1, максимальный 40 символов")
    @NotEmpty
    private String manufacturerName;

    private int yearOfCreation;

    private String country;


}
