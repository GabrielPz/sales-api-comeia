package com.gabrielhenrique.salesapicomeia.modules.itens.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielhenrique.salesapicomeia.exceptions.ItemFoundException;
import com.gabrielhenrique.salesapicomeia.modules.itens.ItensEntity;
import com.gabrielhenrique.salesapicomeia.modules.itens.ItensRepository;

@Service
public class CreateItemService {

    @Autowired
    private ItensRepository itensRepository;

    public ItensEntity execute(ItensEntity itensEntity){
        this.itensRepository.findByName(itensEntity.getName()).ifPresent((item) -> {throw new ItemFoundException();});
        return itensRepository.save(itensEntity);
    }
}