package com.gabrielhenrique.salesapicomeia.modules.itens.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielhenrique.salesapicomeia.modules.itens.ItensEntity;
import com.gabrielhenrique.salesapicomeia.modules.itens.ItensRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/item")
public class ItensController {
    
    @Autowired
    private ItensRepository itensRepository;

    @PostMapping("/create")
    public ItensEntity create(@Valid @RequestBody ItensEntity itensEntity){
        return this.itensRepository.save(itensEntity);
    }
}
