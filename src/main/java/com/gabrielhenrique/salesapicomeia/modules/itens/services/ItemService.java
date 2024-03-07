package com.gabrielhenrique.salesapicomeia.modules.itens.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielhenrique.salesapicomeia.exceptions.ItemFoundException;
import com.gabrielhenrique.salesapicomeia.exceptions.ItemNotFoundException;
import com.gabrielhenrique.salesapicomeia.modules.itens.entity.ItensEntity;
import com.gabrielhenrique.salesapicomeia.modules.itens.repository.ItensRepository;

@Service
public class ItemService {
    @Autowired
    private ItensRepository itensRepository;

    public ItensEntity update(UUID id, ItensEntity updatedItem) {
        ItensEntity existingItem = itensRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException("Item não encontrado: " + id));

        if (updatedItem.getName() != null) {
            existingItem.setName(updatedItem.getName());
        }
        if (updatedItem.getPrice() != null) {
            existingItem.setPrice(updatedItem.getPrice());
        }

        return itensRepository.save(existingItem);
    }

    public ItensEntity create(ItensEntity itensEntity){
        this.itensRepository.findByName(itensEntity.getName()).ifPresent((item) -> {throw new ItemFoundException();});
        return itensRepository.save(itensEntity);
    }

    public void deleteById(UUID id) {
        if (!itensRepository.existsById(id)) {
            throw new ItemNotFoundException("Item não encontrado: " + id);
        }
        itensRepository.deleteById(id);
    }
}
