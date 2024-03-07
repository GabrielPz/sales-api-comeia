package com.gabrielhenrique.salesapicomeia.modules.sales.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielhenrique.salesapicomeia.modules.sales.SalesEntity;
import com.gabrielhenrique.salesapicomeia.modules.sales.services.CreateSaleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sale")
public class SalesController {

    @Autowired
    private CreateSaleService createSaleService;


    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody SalesEntity salesEntity){
        try{
            var result = createSaleService.execute(salesEntity);
            return ResponseEntity.ok().body(result);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
