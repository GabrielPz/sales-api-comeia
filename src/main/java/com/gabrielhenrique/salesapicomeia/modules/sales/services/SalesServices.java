package com.gabrielhenrique.salesapicomeia.modules.sales.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielhenrique.salesapicomeia.exceptions.ItemNotFoundException;
import com.gabrielhenrique.salesapicomeia.exceptions.SaleFoundException;
import com.gabrielhenrique.salesapicomeia.modules.itens.entity.ItensEntity;
import com.gabrielhenrique.salesapicomeia.modules.itens.repository.ItensRepository;
import com.gabrielhenrique.salesapicomeia.modules.sales.entity.SalesEntity;
import com.gabrielhenrique.salesapicomeia.modules.sales.repository.SalesRepository;

@Service
public class SalesServices {
    
    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private ItensRepository itensRepository;


    public SalesEntity create(SalesEntity salesEntity) {
        List<ItensEntity> items = salesEntity.getItemIds().stream()
            .map(id -> itensRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found: " + id)))
            .collect(Collectors.toList());
        salesEntity.setItemsAndCalculateSalePrice(items); // Set item names and calculate sale price
        this.salesRepository.findBySaleDescription(salesEntity.getSaleDescription()).ifPresent((sale) -> {throw new SaleFoundException();});
        return salesRepository.save(salesEntity);
    }

    public SalesEntity update(UUID id, SalesEntity updatedSale) {
        SalesEntity existingSale = salesRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException("Venda não encontrada: " + id));

        if (updatedSale.getSaleDescription() != null) {
            existingSale.setSaleDescription(updatedSale.getSaleDescription());
        }
        if (updatedSale.getItemNames() != null && !updatedSale.getItemNames().isEmpty()) {
            existingSale.setItemNames(updatedSale.getItemNames());
        }
        if (updatedSale.getItemQuantity() != null) {
            existingSale.setItemQuantity(updatedSale.getItemQuantity());
        }
        if (updatedSale.getSalePrice() != null) {
            existingSale.setSalePrice(updatedSale.getSalePrice());
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
