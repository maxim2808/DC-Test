package com.example.DC_Test_Telegin.repositories;

import com.example.DC_Test_Telegin.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
