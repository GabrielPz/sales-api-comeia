package com.gabrielhenrique.salesapicomeia.modules.sales;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

// import jakarta.validation.constraints.Size;
// import jakarta.validation.constraints.Email;

@Data
@Entity(name = "sales")
public class SalesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    @CreationTimestamp
    private LocalDateTime createdAt;
}
