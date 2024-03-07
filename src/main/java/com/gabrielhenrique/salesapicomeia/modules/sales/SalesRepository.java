package com.gabrielhenrique.salesapicomeia.modules.sales;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

// Interface fica melhor com o JPA
public interface SalesRepository extends JpaRepository<SalesEntity, UUID> {
    
}
