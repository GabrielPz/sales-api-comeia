package com.gabrielhenrique.salesapicomeia.modules.itens.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.gabrielhenrique.salesapicomeia.exceptions.ItemFoundException;
import com.gabrielhenrique.salesapicomeia.exceptions.ItemNotFoundException;
import com.gabrielhenrique.salesapicomeia.modules.itens.dto.ItemCreateDTO;
import com.gabrielhenrique.salesapicomeia.modules.itens.dto.ItemUpdateDTO;
import com.gabrielhenrique.salesapicomeia.modules.itens.entity.ItensEntity;
import com.gabrielhenrique.salesapicomeia.modules.itens.repository.ItensRepository;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItensRepository itensRepository;

    private ItemCreateDTO itemCreateDTO;
    private ItemUpdateDTO itemUpdateDTO;
    private UUID itemId;
    @BeforeEach
    void setUp() {
        itemId = UUID.randomUUID();
        itemCreateDTO = new ItemCreateDTO();
        itemCreateDTO.setName("ITEM TESTE");
        itemCreateDTO.setPrice(BigDecimal.valueOf(200.00));

        itemUpdateDTO = new ItemUpdateDTO();
        itemUpdateDTO.setName("ITEM TESTE");
        itemUpdateDTO.setPrice(BigDecimal.valueOf(200.00));

    }

    @Test
    @DisplayName("Should create item successfully")
    public void createItemSuccess() {
        ItensEntity newItem = new ItensEntity();
        newItem.setName("New Item");
        newItem.setPrice(BigDecimal.valueOf(100));

        when(itensRepository.findByName(newItem.getName())).thenReturn(Optional.empty());
        when(itensRepository.save(any(ItensEntity.class))).thenReturn(newItem);

        ItensEntity savedItem = itemService.create(newItem);

        assertNotNull(savedItem);
        assertEquals(newItem.getName(), savedItem.getName());
        verify(itensRepository).save(any(ItensEntity.class));
    }

    @Test
    @DisplayName("Should throw exception when item with the same name already exists")
    public void throwExceptionOnItemNameExist() {
        ItensEntity existingItem = new ItensEntity();
        existingItem.setName("Existing Item");
        existingItem.setPrice(BigDecimal.valueOf(100));

        when(itensRepository.findByName(existingItem.getName())).thenReturn(Optional.of(existingItem));

        assertThrows(ItemFoundException.class, () -> itemService.create(existingItem));

        verify(itensRepository, never()).save(any(ItensEntity.class));
    }

    @Test
    @DisplayName("Should update item successfully")
    public void updateItemSuccess() {
        ItensEntity existingItem = new ItensEntity();
        existingItem.setId(itemId);
        existingItem.setName("Existing Item");
        existingItem.setPrice(BigDecimal.valueOf(100));

        ItensEntity updatedItem = new ItensEntity();
        updatedItem.setName(itemUpdateDTO.getName());
        updatedItem.setPrice(itemUpdateDTO.getPrice());

        when(itensRepository.findById(itemId)).thenReturn(Optional.of(existingItem));
        when(itensRepository.save(any(ItensEntity.class))).thenReturn(updatedItem);

        ItensEntity result = itemService.update(itemId, itemUpdateDTO);

        assertNotNull(result);
        assertEquals(itemUpdateDTO.getName(), result.getName());
        assertEquals(itemUpdateDTO.getPrice(), result.getPrice());
        verify(itensRepository).save(any(ItensEntity.class));
    }

    @Test
    @DisplayName("Should throw exception when updating non-existent item")
    public void updateNonExistentItem() {
        when(itensRepository.findById(itemId)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> itemService.update(itemId, itemUpdateDTO));

        verify(itensRepository, never()).save(any(ItensEntity.class));
    }

    @Test
    @DisplayName("Should delete item successfully")
    public void deleteItemSuccess() {
        UUID itemId = UUID.randomUUID();

        when(itensRepository.existsById(itemId)).thenReturn(true);

        itemService.deleteById(itemId);

        verify(itensRepository).deleteById(itemId);
    }

    @Test
    @DisplayName("Should throw exception when deleting non-existent item")
    public void deleteNonExistentItem() {
        UUID itemId = UUID.randomUUID();

        when(itensRepository.existsById(itemId)).thenReturn(false);

        assertThrows(ItemNotFoundException.class, () -> itemService.deleteById(itemId));

        verify(itensRepository, never()).deleteById(any(UUID.class));
    }
}
