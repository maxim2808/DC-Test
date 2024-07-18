package com.example.DC_Test_Telegin.utils;

import com.example.DC_Test_Telegin.dto.ManufacturerDTO;
import com.example.DC_Test_Telegin.services.ManufacturerService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class ManufacturerValidator implements Validator {
    final ManufacturerService manufacturerService;

    public ManufacturerValidator(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(ManufacturerDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ManufacturerDTO manufacturerDTO = (ManufacturerDTO) target;
        if(manufacturerService.getManufacturerByName(manufacturerDTO.getName()).isPresent()){
            if (manufacturerDTO.getId()!=manufacturerService.getManufacturerByName(manufacturerDTO.getName()).get().getId()){
                errors.rejectValue("name","","-Уже существует производитель с таким именем");
            }
        }

    }
}
