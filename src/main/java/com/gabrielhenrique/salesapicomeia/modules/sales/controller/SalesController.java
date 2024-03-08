package com.gabrielhenrique.salesapicomeia.modules.sales.controller;

import java.util.List;
import java.util.UUID;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielhenrique.salesapicomeia.modules.sales.dto.SalesCreateDTO;
import com.gabrielhenrique.salesapicomeia.modules.sales.dto.SalesUpdateDTO;
import com.gabrielhenrique.salesapicomeia.modules.sales.entity.SalesEntity;
import com.gabrielhenrique.salesapicomeia.modules.sales.services.ListSalesService;
import com.gabrielhenrique.salesapicomeia.modules.sales.services.SalesServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/sale")
@Tag(name = "Vendas", description = "Operações relacionadas a vendas")
public class SalesController {

    @Autowired
    private SalesServices salesServices;

    @Autowired
    private ListSalesService listSalesService;

    @Operation(summary = "Listar todas as vendas", description = "Retorna uma lista com todas as vendas")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Vendas listadas com sucesso", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SalesEntity.class)))
        }),
        @ApiResponse(responseCode = "204", description = "Nenhuma venda encontrada", content = @Content)
    })
    @SecurityRequirement(name = "jwt_auth")
    @GetMapping("/all")
    public ResponseEntity<List<SalesEntity>> listAllSales() {
        List<SalesEntity> sales = listSalesService.listAllSales();
        if (sales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sales);
    }

    @Operation(summary = "Encontrar venda por id", description = "Retorna uma venda com base no ID fornecido")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Venda encontrada",
                content = @Content(schema = @Schema(implementation = SalesEntity.class))),
        @ApiResponse(responseCode = "404", description = "Venda não encontrada")
    })
    @SecurityRequirement(name = "jwt_auth")
    @GetMapping("/by_id/{id}")
    public ResponseEntity<SalesEntity> findSaleById(@PathVariable UUID id) {
        Optional<SalesEntity> sale = listSalesService.findSaleById(id);
        if (!sale.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sale.get());
    }

    @Operation(summary = "Criar venda", description = "Cria uma venda com base nos itemIds fornecidos e a descrição da venda")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Venda criada com sucesso",
                content = @Content(schema = @Schema(implementation = SalesEntity.class))),
        @ApiResponse(responseCode = "400", description = "Venda já existe")
    })
    @SecurityRequirement(name = "jwt_auth")
    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody SalesCreateDTO dto){
        try{
            var result = salesServices.create(dto);
            return ResponseEntity.ok().body(result);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Atualizar venda", description = "Atualiza uma venda com base nos itemIds fornecidos e a descrição da venda")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Venda Atualizada com sucesso",
                content = @Content(schema = @Schema(implementation = SalesEntity.class))),
        @ApiResponse(responseCode = "404", description = "Venda não encontrada"),
        @ApiResponse(responseCode = "400", description = "Erro ao atualizar dados"),
    })
    @SecurityRequirement(name = "jwt_auth")
    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateSale(@PathVariable UUID id, @Valid @RequestBody SalesUpdateDTO dto) {
        try {
            SalesEntity updatedSale = salesServices.update(id, dto);
            return ResponseEntity.ok().body(updatedSale);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Deletar uma venda", description = "Deleta uma venda com base no ID fornecido")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Venda deletada com sucesso",
                content = @Content(schema = @Schema(type = "string"))),
        @ApiResponse(responseCode = "404", description = "Venda não encontrada",
        content = @Content(mediaType = "text/plain", schema = @Schema(type = "string")))
    })
    @SecurityRequirement(name = "jwt_auth")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSaleById(@PathVariable UUID id) {
        try {
            salesServices.deleteById(id);
            return ResponseEntity.ok().body("Venda deletada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
