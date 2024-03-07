package com.gabrielhenrique.salesapicomeia.modules.sales.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielhenrique.salesapicomeia.modules.sales.SalesEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sale")
public class SalesController {

    @GetMapping("/getSales")
    public String getSales() {
        return "Primeira Rota";
    }

    @PostMapping("/create")
    public void create(@Valid @RequestBody SalesEntity salesEntity){
        System.out.println("Venda");
        System.out.println(salesEntity);
    }
}
