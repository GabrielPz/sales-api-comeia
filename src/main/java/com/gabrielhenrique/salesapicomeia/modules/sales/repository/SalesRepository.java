package com.gabrielhenrique.salesapicomeia.modules.sales.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrielhenrique.salesapicomeia.modules.sales.entity.SalesEntity;

public interface SalesRepository extends JpaRepository<SalesEntity, UUID> {
    Optional<SalesEntity> findBySaleDescription(String saleDescription);
    
}
