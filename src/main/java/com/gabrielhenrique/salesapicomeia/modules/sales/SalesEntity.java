package com.gabrielhenrique.salesapicomeia.modules.sales;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

@Data
public class SalesEntity {
    private UUID id;

    @Length(min = 10, max = 200)
    private String saleDescription;
    private String itemsSold;
    private Integer itemQuantity;
    private Double itemPrice;
    private Date saleDate;
}
