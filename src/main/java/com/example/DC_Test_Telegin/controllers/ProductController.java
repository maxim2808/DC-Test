package com.example.DC_Test_Telegin.controllers;

import com.example.DC_Test_Telegin.Services.ProductService;
import com.example.DC_Test_Telegin.dto.ProductDTO;
import com.example.DC_Test_Telegin.dto.ProductManufacturerDTO;
import com.example.DC_Test_Telegin.models.Product;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    final ProductService productService;
    final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public List<ProductDTO> getProducts() {
        return productService.findAll().stream().map(product -> convertToDTO(product)).toList();
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable("id") int id) {
        return convertToDTO(productService.findById(id).get());
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addProduct(@RequestBody ProductManufacturerDTO productManufacturerDTO) {
        int manufacturerId = productManufacturerDTO.getManufacturerID();
        ProductDTO productDTO = productManufacturerDTO.getProductDTO();
        productService.createProduct(convertToProduct(productDTO), manufacturerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<HttpStatus> editProduct(@PathVariable("id") int id, @RequestBody ProductManufacturerDTO productManufacturerDTO) {
        int manufacturerId = productManufacturerDTO.getManufacturerID();
        ProductDTO productDTO = productManufacturerDTO.getProductDTO();
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




}
