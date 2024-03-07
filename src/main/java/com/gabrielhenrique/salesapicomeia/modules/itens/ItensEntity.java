package com.gabrielhenrique.salesapicomeia.modules.itens;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "itens")
public class ItensEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Insira um nome para o item")
    private String name;

    @NotNull(message = "Insira um valor para o item")
    private Double price;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
