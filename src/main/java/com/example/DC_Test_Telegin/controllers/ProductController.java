package com.example.DC_Test_Telegin.controllers;

import com.example.DC_Test_Telegin.services.ManufacturerService;
import com.example.DC_Test_Telegin.services.ProductService;
import com.example.DC_Test_Telegin.dto.ProductDTO;
import com.example.DC_Test_Telegin.dto.ProductManufacturerDTO;
import com.example.DC_Test_Telegin.models.Product;
import com.example.DC_Test_Telegin.utils.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    final ProductService productService;
    final ModelMapper modelMapper;
    final ProductValidator productValidator;

    public ProductController(ProductService productService, ModelMapper modelMapper, ProductValidator productValidator) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.productValidator = productValidator;
    }

    @GetMapping("")
    public List<ProductDTO> getProducts() {
        return productService.findAll().stream().map(product -> convertToDTO(product)).toList();
    }

    @GetMapping("/bymanufacturer/{id}")
    public List<ProductDTO> getManufacturers(@PathVariable("id") int manufacturerId) {
        return productService.findByManufacturer(manufacturerId).stream().map(product -> convertToDTO(product)).toList();
    }


    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable("id") int id) {
        return convertToDTO(productService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addProduct(@RequestBody @Valid ProductManufacturerDTO productManufacturerDTO, BindingResult bindingResult) {
        productValidator.validate(productManufacturerDTO, bindingResult);
        if (bindingResult.hasErrors()) {
           productService.notCreatedMethod(bindingResult);
        }
        int manufacturerId = productManufacturerDTO.getManufacturerID();
        ProductDTO productDTO = productManufacturerDTO.getProduct();
        productService.createProduct(convertToProduct(productDTO), manufacturerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<HttpStatus> editProduct(@PathVariable("id") int id, @RequestBody @Valid ProductManufacturerDTO productManufacturerDTO,
                                                  BindingResult bindingResult) {
        productValidator.validate(productManufacturerDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            productService.notCreatedMethod(bindingResult);
        }
        int manufacturerId = productManufacturerDTO.getManufacturerID();
        ProductDTO productDTO = productManufacturerDTO.getProduct();
        productService.edit(convertToProduct(productDTO), manufacturerId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") int id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    Product convertToProduct(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

    ProductDTO convertToDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> responseEntity(ProductNotFoundException exception){
        ProductErrorResponse message = new ProductErrorResponse("Продукт с таким id не найден", System.currentTimeMillis());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> responseEntity(ProductNotCreatedException exception){
        ProductErrorResponse message =new ProductErrorResponse(exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }

}
