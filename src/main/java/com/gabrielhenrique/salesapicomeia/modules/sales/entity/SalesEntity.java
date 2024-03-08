package com.gabrielhenrique.salesapicomeia.modules.sales.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielhenrique.salesapicomeia.modules.itens.entity.ItensEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;

// import jakarta.validation.constraints.Size;
// import jakarta.validation.constraints.Email;

@Data
@Entity(name = "sales")
public class SalesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Transient  // This field is not persisted in the database
    private List<UUID> itemIds;
    
    @NotBlank(message = "O campo de descrição não pode estar vazio")
    @Length(min = 10, max = 200, message = "A descrição precisa conter entre 10 e 200 carcateres")
    private String saleDescription;

    private Integer itemQuantity;

    private String itemNames; 

    private BigDecimal salePrice;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void setItemsAndCalculateSalePrice(List<ItensEntity> items) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.itemNames = mapper.writeValueAsString(items.stream().map(ItensEntity::getName).collect(Collectors.toList()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing item names", e);
        }

        this.salePrice = BigDecimal.ZERO;
        for (ItensEntity item : items) {
            this.salePrice = this.salePrice.add(item.getPrice());
        }
    }
}
