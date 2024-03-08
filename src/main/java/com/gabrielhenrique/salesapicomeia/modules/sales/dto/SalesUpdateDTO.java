package com.gabrielhenrique.salesapicomeia.modules.sales.dto;


import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
public class SalesUpdateDTO {

    @Schema(example = "Nova Descrição")
    private String saleDescription;
    @Transient  // This field is not persisted in the database
    private List<UUID> itemIds;

}
