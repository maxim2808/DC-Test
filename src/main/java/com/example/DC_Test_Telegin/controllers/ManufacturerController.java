package com.example.DC_Test_Telegin.controllers;

import com.example.DC_Test_Telegin.services.ManufacturerService;
import com.example.DC_Test_Telegin.dto.ManufacturerDTO;
import com.example.DC_Test_Telegin.models.Manufacturer;
import com.example.DC_Test_Telegin.utils.ManufacturerErrorResponse;
import com.example.DC_Test_Telegin.utils.ManufacturerNotCreatedException;
import com.example.DC_Test_Telegin.utils.ManufacturerNotFoundException;
import com.example.DC_Test_Telegin.utils.ManufacturerValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {
    final ManufacturerService manufacturerService;
    final ModelMapper modelMapper;
    final ManufacturerValidator manufacturerValidator;

    public ManufacturerController(ManufacturerService manufacturerService, ModelMapper modelMapper, ManufacturerValidator manufacturerValidator) {
        this.manufacturerService = manufacturerService;
        this.modelMapper = modelMapper;
        this.manufacturerValidator = manufacturerValidator;
    }

    @GetMapping("")
    public List<ManufacturerDTO> getAllManufacturers() {
    return manufacturerService.getAllManufacturers().stream().map(manufacturer -> convertToDTO(manufacturer)).toList();
    }

    @GetMapping("/{id}")
    public ManufacturerDTO getAllManufacturers(@PathVariable int id) {
        return convertToDTO(manufacturerService.getOneManufacturer(id));
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> createManufacturer(@RequestBody @Valid ManufacturerDTO manufacturerDTO, BindingResult bindingResult) {
        manufacturerValidator.validate(manufacturerDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            manufacturerService.notCreatedMethod(bindingResult);
        }
        manufacturerService.saveManufacturer(convertToManufacturer(manufacturerDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<HttpStatus> editManufacturer(@RequestBody @Valid ManufacturerDTO manufacturerDTO, BindingResult bindingResult, @PathVariable int id){
        manufacturerValidator.validate(manufacturerDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            manufacturerService.notCreatedMethod(bindingResult);
        }
        manufacturerService.editManufacturer(convertToManufacturer(manufacturerDTO),id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteManufacturer(@PathVariable int id) {
        manufacturerService.deleteManufacturer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ManufacturerDTO convertToDTO(Manufacturer manufacturer) {
           return modelMapper.map(manufacturer, ManufacturerDTO.class);
    }



    public Manufacturer convertToManufacturer(ManufacturerDTO manufacturerDTO) {
        return modelMapper.map(manufacturerDTO, Manufacturer.class);
    }


    @ExceptionHandler
    public ResponseEntity<ManufacturerErrorResponse> responseEntity(ManufacturerNotFoundException exception){
        ManufacturerErrorResponse message = new ManufacturerErrorResponse("Производитель с таким id не найден", System.currentTimeMillis());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ManufacturerErrorResponse> responseEntity(ManufacturerNotCreatedException exception){
        ManufacturerErrorResponse message =new ManufacturerErrorResponse(exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }










}
