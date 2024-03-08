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
        this.salesRepository.findBySaleDescription(salesEntity.getSaleDescription()).ifPresent((sale) -> {throw new SaleFoundException();});

        List<ItensEntity> items = salesEntity.getItemIds().stream()
            .map(id -> itensRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found: " + id)))
            .collect(Collectors.toList());
        salesEntity.setItemsAndCalculateSalePrice(items);
        salesEntity.setItemQuantity(salesEntity.getItemIds().size());
        return salesRepository.save(salesEntity);
    }

    public SalesEntity update(UUID id, SalesEntity updatedSale) {
        SalesEntity existingSale = salesRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException("Venda não encontrada: " + id));

        if (updatedSale.getSaleDescription() != null) {
            existingSale.setSaleDescription(updatedSale.getSaleDescription());
        }
        
        // Assuming updatedSale contains updated item IDs or we directly use newItemIds parameter
        if (updatedSale.getItemIds() != null && !updatedSale.getItemIds().isEmpty()) {
            List<ItensEntity> items = updatedSale.getItemIds().stream()
                .map(itemId -> itensRepository.findById(itemId)
                    .orElseThrow(() -> new RuntimeException("Item not found: " + itemId)))
                .collect(Collectors.toList());
            existingSale.setItemQuantity(updatedSale.getItemIds().size());
            existingSale.setItemsAndCalculateSalePrice(items);
        }

        if (updatedSale.getItemQuantity() != null) {
            existingSale.setItemQuantity(updatedSale.getItemQuantity());
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
