package com.gabrielhenrique.salesapicomeia.modules.sales.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielhenrique.salesapicomeia.exceptions.ItemNotFoundException;
import com.gabrielhenrique.salesapicomeia.exceptions.SaleFoundException;
import com.gabrielhenrique.salesapicomeia.modules.sales.entity.SalesEntity;
import com.gabrielhenrique.salesapicomeia.modules.sales.repository.SalesRepository;

@Service
public class SalesServices {
    
    @Autowired
    private SalesRepository salesRepository;

    public SalesEntity create(SalesEntity salesEntity){
        this.salesRepository.findBySaleDescription(salesEntity.getSaleDescription()).ifPresent((sale) -> {throw new SaleFoundException();});
        return this.salesRepository.save(salesEntity);
    }

    public SalesEntity update(UUID id, SalesEntity updatedSale) {
        SalesEntity existingSale = salesRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException("Venda não encontrada: " + id));

        if (updatedSale.getSaleDescription() != null) {
            existingSale.setSaleDescription(updatedSale.getSaleDescription());
        }
        if (updatedSale.getItens() != null && !updatedSale.getItens().isEmpty()) {
            existingSale.setItens(updatedSale.getItens());
        }
        if (updatedSale.getItemQuantity() != null) {
            existingSale.setItemQuantity(updatedSale.getItemQuantity());
        }
        if (updatedSale.getItemPrice() != null) {
            existingSale.setItemPrice(updatedSale.getItemPrice());
        }

        return salesRepository.save(existingSale);
    }

    public void deleteById(UUID id) {
        if (!salesRepository.existsById(id)) {
            throw new ItemNotFoundException("Venda não encontrada: " + id);
        }
        salesRepository.deleteById(id);
    }
}
