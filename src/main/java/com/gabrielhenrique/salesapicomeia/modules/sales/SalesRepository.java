package com.gabrielhenrique.salesapicomeia.modules.sales;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<SalesEntity, UUID> {
    Optional<SalesEntity> findBySaleDescription(String saleDescription);
    
}
