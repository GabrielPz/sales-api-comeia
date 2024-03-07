package com.gabrielhenrique.salesapicomeia.modules.sales;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class SalesEntity {
    private UUID id;
    private String saleDescription;
    private String itemsSold;
    private Integer itemQuantity;
    private Double itemPrice;
    private Date saleTime;
}
