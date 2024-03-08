package com.gabrielhenrique.salesapicomeia.modules.sales.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielhenrique.salesapicomeia.modules.sales.entity.SalesEntity;
import com.gabrielhenrique.salesapicomeia.modules.sales.repository.SalesRepository;

@Service
public class ListSalesService {
    
    @Autowired
    private SalesRepository salesRepository;

    public List<SalesEntity> listAllSales() {
        return salesRepository.findAll();
    }

    public Optional<SalesEntity> findSaleById(UUID id) {
        return salesRepository.findById(id);
    }
}
