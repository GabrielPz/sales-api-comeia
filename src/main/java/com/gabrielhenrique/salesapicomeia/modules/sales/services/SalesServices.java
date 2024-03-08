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
import com.gabrielhenrique.salesapicomeia.modules.sales.dto.SalesCreateDTO;
import com.gabrielhenrique.salesapicomeia.modules.sales.dto.SalesUpdateDTO;
import com.gabrielhenrique.salesapicomeia.modules.sales.entity.SalesEntity;
import com.gabrielhenrique.salesapicomeia.modules.sales.repository.SalesRepository;

@Service
public class SalesServices {
    
    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private ItensRepository itensRepository;


    public SalesEntity create(SalesCreateDTO dto) {
        this.salesRepository.findBySaleDescription(dto.getSaleDescription()).ifPresent((sale) -> {throw new SaleFoundException();});
        SalesEntity salesEntity = new SalesEntity();

        List<ItensEntity> items = dto.getItemIds().stream()
            .map(id -> itensRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found: " + id)))
            .collect(Collectors.toList());

        salesEntity.setItemsAndCalculateSalePrice(items);
        salesEntity.setItemQuantity(dto.getItemIds().size());
        salesEntity.setSaleDescription(dto.getSaleDescription());
        return salesRepository.save(salesEntity);
    }

    public SalesEntity update(UUID id, SalesUpdateDTO dto) {
        SalesEntity existingSale = salesRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Sale not found: " + id));

        if(dto.getSaleDescription() != null && !dto.getSaleDescription().isEmpty()) {
            existingSale.setSaleDescription(dto.getSaleDescription());
        }

        if(dto.getItemIds() != null && !dto.getItemIds().isEmpty()) {
            List<ItensEntity> items = dto.getItemIds().stream()
                .map(itemId -> itensRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException("Item not found: " + itemId)))
                .collect(Collectors.toList());
            existingSale.setItemsAndCalculateSalePrice(items);
            existingSale.setItemQuantity(items.size());
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
