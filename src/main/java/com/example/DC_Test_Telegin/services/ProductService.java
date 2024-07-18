package com.example.DC_Test_Telegin.services;

import com.example.DC_Test_Telegin.repositories.ProductRepository;
import com.example.DC_Test_Telegin.models.Manufacturer;
import com.example.DC_Test_Telegin.models.Product;
import com.example.DC_Test_Telegin.utils.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {
    final ProductRepository productRepository;
    final ManufacturerService manufacturerService;

    public ProductService(ProductRepository productRepository, ManufacturerService manufacturerService) {
        this.productRepository = productRepository;
        this.manufacturerService = manufacturerService;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(int id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }


    @Transactional
    public void createProduct (Product product, int manufacturerId) {
        Manufacturer manufacturer = manufacturerService.getOneManufacturer(manufacturerId);
        product.setRegistrationDate(LocalDateTime.now());
        product.setManufacturer(manufacturer);
        productRepository.save(product);
    }

    @Transactional
    public void edit(Product product, int manufacturerId, int id) {
        Manufacturer manufacturer = manufacturerService.getOneManufacturer(manufacturerId);
        Product oldProduct = productRepository.findById(id).get();
        product.setManufacturer(manufacturer);
        product.setRegistrationDate(oldProduct.getRegistrationDate());
        product.setId(id);
        productRepository.save(product);
    }

    @Transactional
    public void delete(int id) {
        productRepository.deleteById(id);
    }




}
