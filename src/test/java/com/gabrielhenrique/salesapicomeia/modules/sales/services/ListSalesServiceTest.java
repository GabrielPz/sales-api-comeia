package com.gabrielhenrique.salesapicomeia.modules.sales.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.gabrielhenrique.salesapicomeia.modules.sales.entity.SalesEntity;
import com.gabrielhenrique.salesapicomeia.modules.sales.repository.SalesRepository;

@ExtendWith(MockitoExtension.class)
public class ListSalesServiceTest {

    @InjectMocks
    private ListSalesService listSalesService;

    @Mock
    private SalesRepository salesRepository;

    private SalesEntity sale;

    @BeforeEach
    public void setUp() {
        sale = new SalesEntity();
        // Initialize your sale object with test data here
    }

    @Test
    @DisplayName("Should list all sales")
    public void listAllSales() {
        when(salesRepository.findAll()).thenReturn(Arrays.asList(sale));

        List<SalesEntity> sales = listSalesService.listAllSales();

        assertFalse(sales.isEmpty());
        assertEquals(1, sales.size());
        verify(salesRepository).findAll();
    }

    @Test
    @DisplayName("Should find sale by id")
    public void findSaleById() {
        UUID id = UUID.randomUUID();
        when(salesRepository.findById(id)).thenReturn(Optional.of(sale));

        Optional<SalesEntity> foundSale = listSalesService.findSaleById(id);

        assertTrue(foundSale.isPresent());
        assertEquals(sale, foundSale.get());
        verify(salesRepository).findById(id);
    }

    @Test
    @DisplayName("Should return an empty list when no sales are found")
    public void returnEmptyListWhenNoSalesFound() {
        when(salesRepository.findAll()).thenReturn(Arrays.asList());

        List<SalesEntity> sales = listSalesService.listAllSales();

        assertTrue(sales.isEmpty());
        verify(salesRepository).findAll();
    }

    @Test
    @DisplayName("Should return empty Optional when sale by ID is not found")
    public void returnEmptyOptionalWhenSaleByIdNotFound() {
        UUID id = UUID.randomUUID();
        when(salesRepository.findById(id)).thenReturn(Optional.empty());

        Optional<SalesEntity> foundSale = listSalesService.findSaleById(id);

        assertFalse(foundSale.isPresent());
        verify(salesRepository).findById(id);
    }
}
