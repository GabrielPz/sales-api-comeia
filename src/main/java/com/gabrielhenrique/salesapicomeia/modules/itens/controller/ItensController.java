package com.gabrielhenrique.salesapicomeia.modules.itens.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielhenrique.salesapicomeia.exceptions.ItemNotFoundException;
import com.gabrielhenrique.salesapicomeia.modules.itens.dto.ItemCreateDTO;
import com.gabrielhenrique.salesapicomeia.modules.itens.dto.ItemUpdateDTO;
import com.gabrielhenrique.salesapicomeia.modules.itens.entity.ItensEntity;
import com.gabrielhenrique.salesapicomeia.modules.itens.services.ItemService;
import com.gabrielhenrique.salesapicomeia.modules.itens.services.ListItensService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/item")
@Tag(name = "Itens", description = "Operações relacionadas a itens")
public class ItensController {
    
    @Autowired
    private ItemService itemService;

    @Autowired
    private ListItensService listItensService;


    //Criação do item
    @Operation(
        summary = "Criação de um item no banco de dados", 
        description = "Cria um Item")
    @PostMapping("/create")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Item criado com sucesso", content = {
            @Content(schema = @Schema(implementation = ItensEntity.class))
        }),
        @ApiResponse(responseCode = "400", description = "Item já existe", content = @Content(schema = @Schema(type = "string")))
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> create(@Valid @RequestBody ItemCreateDTO itemCreateDTO) {
        try {
            ItensEntity item = new ItensEntity();
            item.setName(itemCreateDTO.getName());
            item.setPrice(itemCreateDTO.getPrice());

            var result = this.itemService.create(item);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    //Atualização de item
    @Operation(summary = "Atualização de um item no banco de dados", description = "Atualiza um Item")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Item atualizado com sucesso", content = {
            @Content(schema = @Schema(implementation = ItensEntity.class))
        }),
        @ApiResponse(responseCode = "404", description = "Item não encontrado", content = @Content(schema = @Schema(type = "string")))
    })
    @SecurityRequirement(name = "jwt_auth")
    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> patchUpdate(@PathVariable UUID id, @Valid @RequestBody ItemUpdateDTO itemUpdateDTO) {
        try {
            ItensEntity updatedItem = itemService.update(id, itemUpdateDTO);
            return ResponseEntity.ok().body(updatedItem);
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    //Deletar Item
    @Operation(summary = "Deletar um item do banco de dados", description = "Deleta um Item pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Item deletado com sucesso", content = @Content(schema = @Schema(type = "string"))),
        @ApiResponse(responseCode = "404", description = "Item não encontrado", content = @Content(schema = @Schema(type = "string")))
    })
    @SecurityRequirement(name = "jwt_auth")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id) {
        try {
            itemService.deleteById(id);
            return ResponseEntity.ok().body("Item deletado com sucesso.");
        } catch (ItemNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    


    //Listar Todos os itens
    @Operation(summary = "Listar todos os itens", description = "Retorna uma lista de todos os itens cadastrados no banco de dados")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Itens listados com sucesso", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ItensEntity.class)))
        }),
        @ApiResponse(responseCode = "204", description = "Nenhum item encontrado", content = @Content)
    })
    @SecurityRequirement(name = "jwt_auth")
    @GetMapping("/all")
    public ResponseEntity<List<ItensEntity>> listAll() {
        try{
            var result = this.listItensService.listAll();
            if(result.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok().body(result);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }


    //Listar Item por id
    @Operation(summary = "Buscar um item pelo ID", description = "Retorna o item correspondente ao ID fornecido")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Item encontrado com sucesso", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ItensEntity.class))
        }),
        @ApiResponse(responseCode = "404", description = "Item não encontrado", content = @Content(schema = @Schema(type = "string")))
    })
    @SecurityRequirement(name = "jwt_auth")
    @GetMapping("/by_id/{id}")
    public ResponseEntity<Optional<ItensEntity>> listById(@Valid @PathVariable UUID id) {
        try{
            var result = this.listItensService.listById(id);
            if(result.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok().body(result);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }
    

}
