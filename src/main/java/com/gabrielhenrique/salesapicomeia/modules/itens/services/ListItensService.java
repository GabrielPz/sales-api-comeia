package com.gabrielhenrique.salesapicomeia.modules.itens.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielhenrique.salesapicomeia.modules.itens.entity.ItensEntity;
import com.gabrielhenrique.salesapicomeia.modules.itens.repository.ItensRepository;

@Service
public class ListItensService {
    @Autowired
    private ItensRepository itensRepository;

    public List<ItensEntity> execute(){

        List<ItensEntity> itensList = new ArrayList<>();
        itensRepository.findAll().forEach(itensList::add);

        return itensList;
    }
}
