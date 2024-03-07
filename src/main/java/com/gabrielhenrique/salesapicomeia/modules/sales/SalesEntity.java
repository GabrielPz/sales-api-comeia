package com.gabrielhenrique.salesapicomeia.modules.sales;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;

// import jakarta.validation.constraints.Size;
// import jakarta.validation.constraints.Email;

@Data
public class SalesEntity {
    private UUID id;

    @NotNull(message = "O campo id não pode ser nulo.")
    @Length(min = 10, max = 200, message = "A descrição precis conter entre 10 e 200 carcateres")
    private String saleDescription;

    @NotNull(message = "O campo id não pode ser nulo.")
    private String itemsSold;

    @NotNull(message = "O campo id não pode ser nulo.")
    private Integer itemQuantity;

    @NotNull(message = "O campo id não pode ser nulo.")
    private Double itemPrice;

    @NotNull(message = "O campo id não pode ser nulo.")
    private Date saleDate;
}
