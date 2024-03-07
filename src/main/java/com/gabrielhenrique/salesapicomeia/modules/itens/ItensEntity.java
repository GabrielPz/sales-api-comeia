package com.gabrielhenrique.salesapicomeia.modules.itens;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "itens")
public class ItensEntity {
    private UUID id;

    @NotNull(message = "Insira um nome para o item")
    private String name;

    @NotNull(message = "Insira um valor para o item")
    private Double price;
}
