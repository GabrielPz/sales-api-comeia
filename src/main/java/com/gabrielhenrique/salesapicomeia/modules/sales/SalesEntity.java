package com.gabrielhenrique.salesapicomeia.modules.sales;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import com.gabrielhenrique.salesapicomeia.modules.itens.entity.ItensEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

    // @NotNull(message = "O campo id não pode ser nulo.")
    // private String itemsSold;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<ItensEntity> itens;

    @NotNull(message = "O campo id não pode ser nulo.")
    private Integer itemQuantity;

    @NotNull(message = "O campo id não pode ser nulo.")
    private Double itemPrice;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
