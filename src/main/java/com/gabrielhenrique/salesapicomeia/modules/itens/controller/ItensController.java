package com.gabrielhenrique.salesapicomeia.modules.itens.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielhenrique.salesapicomeia.exceptions.ItemNotFoundException;
import com.gabrielhenrique.salesapicomeia.modules.itens.entity.ItensEntity;
import com.gabrielhenrique.salesapicomeia.modules.itens.services.CreateItemService;
import com.gabrielhenrique.salesapicomeia.modules.itens.services.ListItensService;
import com.gabrielhenrique.salesapicomeia.modules.itens.services.UpdateItemService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/item")
public class ItensController {
    
    @Autowired
    private CreateItemService createItemService;

    @Autowired
    private ListItensService listItensService;

    @Autowired
    private UpdateItemService updateItemService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody ItensEntity itensEntity){
        try{
            var result = this.createItemService.execute(itensEntity);
            return ResponseEntity.ok().body(result);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> patchUpdate(@PathVariable UUID id, @Valid @RequestBody ItensEntity itensEntity) {
        try {
            ItensEntity updatedItem = updateItemService.execute(id, itensEntity);
            return ResponseEntity.ok().body(updatedItem);
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ItensEntity>> listAll() {
        try{
            var result = this.listItensService.listAll();
            if(result.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok().body(result);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/by_id/{id}")
    public ResponseEntity<Optional<ItensEntity>> listById(@Valid @PathVariable UUID id) {
        try{
            var result = this.listItensService.listById(id);
            if(result.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok().body(result);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }
    

}
