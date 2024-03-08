package com.gabrielhenrique.salesapicomeia.modules.sales.controller;

import java.util.List;
import java.util.UUID;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielhenrique.salesapicomeia.modules.sales.entity.SalesEntity;
import com.gabrielhenrique.salesapicomeia.modules.sales.services.ListSalesService;
import com.gabrielhenrique.salesapicomeia.modules.sales.services.SalesServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sale")
public class SalesController {

    @Autowired
    private SalesServices salesServices;

    @Autowired
    private ListSalesService listSalesService;

    @GetMapping("/all")
    public ResponseEntity<List<SalesEntity>> listAllSales() {
        List<SalesEntity> sales = listSalesService.listAllSales();
        if (sales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/by_id/{id}")
    public ResponseEntity<SalesEntity> findSaleById(@PathVariable UUID id) {
        Optional<SalesEntity> sale = listSalesService.findSaleById(id);
        if (!sale.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sale.get());
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody SalesEntity salesEntity){
        try{
            var result = salesServices.create(salesEntity);
            return ResponseEntity.ok().body(result);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateSale(@PathVariable UUID id, @Valid @RequestBody SalesEntity salesEntity) {
        try {
            SalesEntity updatedSale = salesServices.update(id, salesEntity);
            return ResponseEntity.ok().body(updatedSale);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSaleById(@PathVariable UUID id) {
        try {
            salesServices.deleteById(id);
            return ResponseEntity.ok().body("Sale successfully deleted.");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
