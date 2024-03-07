package com.gabrielhenrique.salesapicomeia.modules.sales;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

//Não preciso utilizar a annotattion @Repository pois já estou extendendo de JPARepository
public interface SalesRepository extends JpaRepository<SalesEntity, UUID> {
    //Só de utilizar o findBy o spring já entende que precisa realizar um select, caso eu precise de mais um parametro é só utilizar OR ou AND
    Optional<SalesEntity> findBySaleDescription(String saleDescription);
    
}
