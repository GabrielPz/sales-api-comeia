package com.gabrielhenrique.salesapicomeia.modules.itens.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielhenrique.salesapicomeia.exceptions.ItemNotFoundException;
import com.gabrielhenrique.salesapicomeia.modules.itens.entity.ItensEntity;
import com.gabrielhenrique.salesapicomeia.modules.itens.repository.ItensRepository;

@Service
public class UpdateItemService {

    @Autowired
    private ItensRepository itensRepository;

    public ItensEntity execute(UUID id, ItensEntity updatedItem) {
        ItensEntity existingItem = itensRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException("Item not found with ID: " + id));

        // Only update fields that are present (non-null) in the updatedItem
        if (updatedItem.getName() != null) {
            existingItem.setName(updatedItem.getName());
        }
        if (updatedItem.getPrice() != null) {
            existingItem.setPrice(updatedItem.getPrice());
        }
        // Add similar checks for other updatable fields

        return itensRepository.save(existingItem);
    }
}