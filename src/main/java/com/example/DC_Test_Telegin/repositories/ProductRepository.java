package com.example.DC_Test_Telegin.repositories;

import com.example.DC_Test_Telegin.models.Manufacturer;
import com.example.DC_Test_Telegin.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findProductByName(String productName);
    List<Product> findProductByManufacturer(Manufacturer manufacturer);
}
