package com.gabrielhenrique.salesapicomeia.modules.sales.controllers;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielhenrique.salesapicomeia.exceptions.SaleFoundException;
import com.gabrielhenrique.salesapicomeia.modules.sales.SalesEntity;
import com.gabrielhenrique.salesapicomeia.modules.sales.SalesRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sale")
public class SalesController {

    // Deixo a responsibilidade de instanciar o repositorio para o string
    @Autowired
    private SalesRepository salesRepository;


    @PostMapping("/create")
    public SalesEntity create(@Valid @RequestBody SalesEntity salesEntity){
        this.salesRepository.findBySaleDescription(salesEntity.getSaleDescription()).ifPresent((sale) -> {throw new SaleFoundException();});
        return this.salesRepository.save(salesEntity);
    }
}
