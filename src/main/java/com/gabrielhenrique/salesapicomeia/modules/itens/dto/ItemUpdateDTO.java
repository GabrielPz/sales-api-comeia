package com.gabrielhenrique.salesapicomeia.modules.itens.dto;


import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ItemUpdateDTO {

    @Schema(example = "TV")
    private String name;

    @Schema(example = "3500")
    private BigDecimal price;
}
