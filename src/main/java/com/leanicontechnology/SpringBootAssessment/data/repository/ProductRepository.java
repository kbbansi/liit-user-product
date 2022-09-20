package com.leanicontechnology.SpringBootAssessment.data.repository;

import com.leanicontechnology.SpringBootAssessment.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByProductName(String productName);

    Optional<ProductEntity> findByProductUUID(String productUUID);
}