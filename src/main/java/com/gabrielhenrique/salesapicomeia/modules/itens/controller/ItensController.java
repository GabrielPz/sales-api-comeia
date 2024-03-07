package com.gabrielhenrique.salesapicomeia.modules.itens.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielhenrique.salesapicomeia.modules.itens.entity.ItensEntity;
import com.gabrielhenrique.salesapicomeia.modules.itens.services.CreateItemService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/item")
public class ItensController {
    
    @Autowired
    private CreateItemService createItemService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody ItensEntity itensEntity){
        try{
            var result = this.createItemService.execute(itensEntity);
            return ResponseEntity.ok().body(result);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
