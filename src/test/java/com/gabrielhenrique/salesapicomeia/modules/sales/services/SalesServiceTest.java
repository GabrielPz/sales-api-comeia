package com.gabrielhenrique.salesapicomeia.modules.sales.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;
import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gabrielhenrique.salesapicomeia.exceptions.ItemNotFoundException;
import com.gabrielhenrique.salesapicomeia.exceptions.SaleFoundException;
import com.gabrielhenrique.salesapicomeia.modules.itens.entity.ItensEntity;
import com.gabrielhenrique.salesapicomeia.modules.itens.repository.ItensRepository;
import com.gabrielhenrique.salesapicomeia.modules.sales.entity.SalesEntity;
import com.gabrielhenrique.salesapicomeia.modules.sales.repository.SalesRepository;

@ExtendWith(MockitoExtension.class)
public class SalesServiceTest {

    @InjectMocks
    private SalesServices salesServices;

    @Mock
    private SalesRepository salesRepository;

    @Mock
    private ItensRepository itensRepository;

    private UUID itemId1, itemId2;
    private ItensEntity item1, item2;
    private SalesEntity salesEntity;
    private UUID saleId;

    @BeforeEach
    void setUp() {
        itemId1 = UUID.randomUUID();
        itemId2 = UUID.randomUUID();
        saleId = UUID.randomUUID();

        item1 = new ItensEntity();
        item1.setId(itemId1);
        item1.setName("Item1");
        item1.setPrice(BigDecimal.valueOf(100.00));

        item2 = new ItensEntity();
        item2.setId(itemId2);
        item2.setName("Item2");
        item2.setPrice(BigDecimal.valueOf(200.00));

        salesEntity = new SalesEntity();
        salesEntity.setSaleDescription("Sale Description");
        salesEntity.setItemIds(Arrays.asList(itemId1, itemId2));
    }

    @Test
    @DisplayName("Should create a new sale successfully")
    public void shouldCreateNewSaleSuccessfully() {
        when(salesRepository.findBySaleDescription(anyString())).thenReturn(Optional.empty());
        when(itensRepository.findById(itemId1)).thenReturn(Optional.of(item1));
        when(itensRepository.findById(itemId2)).thenReturn(Optional.of(item2));
        when(salesRepository.save(any(SalesEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        SalesEntity createdSale = salesServices.create(salesEntity);

        assertNotNull(createdSale);
        assertEquals(2, createdSale.getItemQuantity());
        verify(salesRepository).save(any(SalesEntity.class));
    }

    @Test
    @DisplayName("Should throw SaleFoundException if sale description already exists")
    public void shouldThrowSaleFoundExceptionIfDescriptionExists() {
        when(salesRepository.findBySaleDescription(anyString())).thenReturn(Optional.of(salesEntity));

        assertThrows(SaleFoundException.class, () -> salesServices.create(salesEntity));
    }

    @Test
    @DisplayName("Should update an existing sale")
    public void shouldUpdateExistingSale() {
        when(salesRepository.findById(saleId)).thenReturn(Optional.of(salesEntity));
        when(itensRepository.findById(itemId1)).thenReturn(Optional.of(item1));
        when(itensRepository.findById(itemId2)).thenReturn(Optional.of(item2));
        when(salesRepository.save(any(SalesEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        SalesEntity updatedSale = salesEntity;
        updatedSale.setSaleDescription("Updated Description");

        SalesEntity result = salesServices.update(saleId, updatedSale);

        assertNotNull(result);
        assertEquals("Updated Description", result.getSaleDescription());
        verify(salesRepository).save(any(SalesEntity.class));
    }

    @Test
    @DisplayName("Should throw ItemNotFoundException if sale to update does not exist")
    public void shouldThrowItemNotFoundExceptionWhenUpdateIfSaleDoesNotExist() {
        when(salesRepository.findById(saleId)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> salesServices.update(saleId, new SalesEntity()));
    }

    @Test
    @DisplayName("Should delete an existing sale")
    public void shouldDeleteExistingSale() {
        when(salesRepository.existsById(saleId)).thenReturn(true);

        salesServices.deleteById(saleId);

        verify(salesRepository).deleteById(saleId);
    }

    @Test
    @DisplayName("Should throw ItemNotFoundException if sale to delete does not exist")
    public void shouldThrowItemNotFoundExceptionWhenDeleteIfSaleDoesNotExist() {
        when(salesRepository.existsById(saleId)).thenReturn(false);

        assertThrows(ItemNotFoundException.class, () -> salesServices.deleteById(saleId));
    }
}
