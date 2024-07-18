package com.example.DC_Test_Telegin.utils;

import com.example.DC_Test_Telegin.dto.ProductDTO;
import com.example.DC_Test_Telegin.dto.ProductManufacturerDTO;
import com.example.DC_Test_Telegin.models.Product;
import com.example.DC_Test_Telegin.services.ProductService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {
    final ProductService productService;

    public ProductValidator(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(ProductManufacturerDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

   ProductManufacturerDTO productManufacturerDTO=  (ProductManufacturerDTO) target;
        ProductDTO productDTO = productManufacturerDTO.getProduct();
    if(productService.findByName(productDTO.getName()).isPresent()){
        if(productDTO.getId()!=productService.findByName(productDTO.getName()).get().getId()){
            errors.rejectValue("product","","-Продукт с таким именем уже существует");
        }
    }
    }
}
