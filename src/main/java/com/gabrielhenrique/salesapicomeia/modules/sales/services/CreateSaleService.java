package com.gabrielhenrique.salesapicomeia.modules.sales.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielhenrique.salesapicomeia.exceptions.SaleFoundException;
import com.gabrielhenrique.salesapicomeia.modules.sales.SalesEntity;
import com.gabrielhenrique.salesapicomeia.modules.sales.SalesRepository;

@Service
public class CreateSaleService {
    
    @Autowired
    private SalesRepository salesRepository;

    public SalesEntity execute(SalesEntity salesEntity){
        this.salesRepository.findBySaleDescription(salesEntity.getSaleDescription()).ifPresent((sale) -> {throw new SaleFoundException();});
        return this.salesRepository.save(salesEntity);
    }
}
