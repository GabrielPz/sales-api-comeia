package com.gabrielhenrique.salesapicomeia.modules.itens.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity(name = "itens")
public class ItensEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // @Schema(example = "TV")
    @NotBlank(message = "Insira um nome para o item")
    private String name;

    // @Schema(example = "250.5")
    @Digits(integer=6, fraction=2, message = "O preço deve ser um valor numérico com até 6 dígitos inteiros e até 2 casas decimais.")
    private BigDecimal price;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
