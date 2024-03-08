package com.gabrielhenrique.salesapicomeia.modules.itens.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class ItemCreateDTO {
    @NotBlank(message = "Insira um nome para o item")
    @Schema(example = "TV")
    private String name;

    @Schema(example = "2500")
    @Digits(integer=6, fraction=2, message = "O preço deve ser um valor numérico com até 6 dígitos inteiros e até 2 casas decimais.")
    private BigDecimal price;

}
