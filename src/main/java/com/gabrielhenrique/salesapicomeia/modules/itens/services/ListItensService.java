package com.gabrielhenrique.salesapicomeia.modules.itens.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielhenrique.salesapicomeia.modules.itens.entity.ItensEntity;
import com.gabrielhenrique.salesapicomeia.modules.itens.repository.ItensRepository;

@Service
public class ListItensService {
    @Autowired
    private ItensRepository itensRepository;

    public List<ItensEntity> listAll(){
        List<ItensEntity> itensList = new ArrayList<>();
        itensRepository.findAll().forEach(itensList::add);
        return itensList;
    }

    public Optional<ItensEntity> listById(UUID id){
        return itensRepository.findById(id);
    }
}
