package com.example.DC_Test_Telegin.controllers;

import com.example.DC_Test_Telegin.Services.ManufacturerService;
import com.example.DC_Test_Telegin.dto.ManufacturerDTO;
import com.example.DC_Test_Telegin.models.Manufacturer;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {
    final ManufacturerService manufacturerService;
    final ModelMapper modelMapper;

    public ManufacturerController(ManufacturerService manufacturerService, ModelMapper modelMapper) {
        this.manufacturerService = manufacturerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public List<ManufacturerDTO> getAllManufacturers() {
    return manufacturerService.getAllManufacturers().stream().map(manufacturer -> convertToDTO(manufacturer)).toList();
    }

    @GetMapping("/{id}")
    public ManufacturerDTO getAllManufacturers(@PathVariable int id) {
        return convertToDTO(manufacturerService.getOneManufacturer(id).get());
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> createManufacturer(@RequestBody ManufacturerDTO manufacturerDTO) {
        manufacturerService.saveManufacturer(convertToManufacturer(manufacturerDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<HttpStatus> editManufacturer(@RequestBody ManufacturerDTO manufacturerDTO, @PathVariable int id){
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








}
