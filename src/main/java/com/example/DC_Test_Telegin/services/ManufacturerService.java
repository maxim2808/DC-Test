package com.example.DC_Test_Telegin.services;

import com.example.DC_Test_Telegin.repositories.ManufacturerRepository;
import com.example.DC_Test_Telegin.models.Manufacturer;
import com.example.DC_Test_Telegin.utils.ManufacturerNotCreatedException;
import com.example.DC_Test_Telegin.utils.ManufacturerNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ManufacturerService {
    final ManufacturerRepository manufacturerRepository;

    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public Manufacturer getOneManufacturer(int id) {
        return manufacturerRepository.findById(id).orElseThrow(ManufacturerNotFoundException::new);
    }

//    public Optional<Manufacturer> getManufacturerByName(String name) {
//        return manufacturerRepository.findByManufacturerByName(name);
//    }

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    @Transactional
    public void saveManufacturer(Manufacturer manufacturer) {
        manufacturer.setRegistrationDate(LocalDateTime.now());
        manufacturerRepository.save(manufacturer);
    }

    @Transactional
    public void  editManufacturer(Manufacturer manufacturer, int id) {
        Manufacturer oldManufacturer = getOneManufacturer(id);
        manufacturer.setRegistrationDate(oldManufacturer.getRegistrationDate());
        manufacturer.setId(id);
        manufacturerRepository.save(manufacturer);
    }

    @Transactional
    public void deleteManufacturer(int id) {
        manufacturerRepository.deleteById(id);
    }


    public void notCreatedMethod(BindingResult bindingResult){
        StringBuilder errorsMessage = new StringBuilder();
        for(FieldError error : bindingResult.getFieldErrors()){
            errorsMessage.append(error.getField() + " " +error.getDefaultMessage() + ";");
        }
        throw new ManufacturerNotCreatedException(errorsMessage.toString());
    }

}
