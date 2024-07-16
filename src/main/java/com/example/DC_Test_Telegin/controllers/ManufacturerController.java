package com.example.DC_Test_Telegin.controllers;

import com.example.DC_Test_Telegin.Services.ManufacturerService;
import com.example.DC_Test_Telegin.models.Manufacturer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {
    final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("")
    public List<Manufacturer> getAllManufacturers() {
    return manufacturerService.getAllManufacturers();
    }

    @GetMapping("/{id}")
    public Manufacturer getAllManufacturers(@PathVariable int id) {
        return manufacturerService.getOneManufacturer(id).get();
    }

    @PostMapping("/add")
    public ResponseEntity<Manufacturer> createManufacturer(@RequestBody Manufacturer manufacturer) {
        manufacturerService.saveManufacturer(manufacturer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<Manufacturer> editManufacturer(@RequestBody Manufacturer manufacturer, @PathVariable int id){
        manufacturerService.editManufacturer(manufacturer,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Manufacturer> deleteManufacturer(@PathVariable int id) {
        manufacturerService.deleteManufacturer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }








}
