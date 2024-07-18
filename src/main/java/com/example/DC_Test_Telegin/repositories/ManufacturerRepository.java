package com.example.DC_Test_Telegin.repositories;

import com.example.DC_Test_Telegin.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {
//    Optional<Manufacturer> findByManufacturerByName(String manufacturerName);

}
