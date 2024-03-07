package com.gabrielhenrique.salesapicomeia.modules.itens.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielhenrique.salesapicomeia.modules.itens.entity.ItensEntity;
import com.gabrielhenrique.salesapicomeia.modules.itens.services.CreateItemService;
import com.gabrielhenrique.salesapicomeia.modules.itens.services.ListItensService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/item")
public class ItensController {
    
    @Autowired
    private CreateItemService createItemService;

    @Autowired
    private ListItensService listItensService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody ItensEntity itensEntity){
        try{
            var result = this.createItemService.execute(itensEntity);
            return ResponseEntity.ok().body(result);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ItensEntity>> list() {
        try{
            var result = this.listItensService.execute();
            if(result.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok().body(result);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }
    

}
