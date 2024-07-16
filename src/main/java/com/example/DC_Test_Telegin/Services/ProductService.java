package com.example.DC_Test_Telegin.Services;

import com.example.DC_Test_Telegin.Repositories.ProductRepository;
import com.example.DC_Test_Telegin.models.Manufacturer;
import com.example.DC_Test_Telegin.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }


    @Transactional
    public void save(Product product, int manufacturerId) {
        Manufacturer manufacturer = manufacturerService.getOneManufacturer(manufacturerId).get();
        product.setManufacturer(manufacturer);
        productRepository.save(product);
    }

    @Transactional
    public void edit(Product product, int id, int manufacturerId) {
        Manufacturer manufacturer = manufacturerService.getOneManufacturer(manufacturerId).get();
        product.setManufacturer(manufacturer);
        product.setId(id);
        productRepository.save(product);
    }

    @Transactional
    public void delete(int id) {
        productRepository.deleteById(id);
    }


}
