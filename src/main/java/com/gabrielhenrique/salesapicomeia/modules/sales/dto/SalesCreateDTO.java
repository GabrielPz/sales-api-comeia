package com.gabrielhenrique.salesapicomeia.modules.sales.dto;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SalesCreateDTO {
    @Schema(example = "Primeira venda")
    private String saleDescription;
    private List<UUID> itemIds;

}
