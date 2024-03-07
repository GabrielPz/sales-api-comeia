package com.gabrielhenrique.salesapicomeia.modules.itens;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

// Não preciso utilizar a annotattion @Repository pois já estou extendendo de JPARepository
public interface ItensRepository extends JpaRepository<ItensEntity, UUID> {
    Optional<ItensEntity> findByName(String name);
}
