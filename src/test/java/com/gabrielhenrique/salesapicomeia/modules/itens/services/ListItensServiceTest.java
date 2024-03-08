package com.gabrielhenrique.salesapicomeia.modules.itens.services;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gabrielhenrique.salesapicomeia.modules.itens.entity.ItensEntity;
import com.gabrielhenrique.salesapicomeia.modules.itens.repository.ItensRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ListItensServiceTest {

    @InjectMocks
    private ListItensService listItensService;

    @Mock
    private ItensRepository itensRepository;

    @Test
    @DisplayName("Should list all items successfully")
    public void listAllItemsSuccess() {
        List<ItensEntity> mockItemList = new ArrayList<>();
        mockItemList.add(new ItensEntity());
        mockItemList.add(new ItensEntity());

        when(itensRepository.findAll()).thenReturn(mockItemList);

        List<ItensEntity> resultList = listItensService.listAll();

        assertEquals(2, resultList.size());
        verify(itensRepository).findAll();
    }

    @Test
    @DisplayName("Should list item by ID successfully")
    public void listItemByIdSuccess() {
        UUID itemId = UUID.randomUUID();
        ItensEntity mockItem = new ItensEntity();
        mockItem.setId(itemId);

        when(itensRepository.findById(itemId)).thenReturn(Optional.of(mockItem));

        Optional<ItensEntity> resultItem = listItensService.listById(itemId);

        assertTrue(resultItem.isPresent());
        assertEquals(itemId, resultItem.get().getId());
        verify(itensRepository).findById(itemId);
    }

    @Test
    @DisplayName("Should return empty when listing item by non-existent ID")
    public void listItemByNonExistentId() {
        UUID itemId = UUID.randomUUID();

        when(itensRepository.findById(itemId)).thenReturn(Optional.empty());

        Optional<ItensEntity> resultItem = listItensService.listById(itemId);

        assertTrue(resultItem.isEmpty());
        verify(itensRepository).findById(itemId);
    }
}
