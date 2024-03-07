package com.gabrielhenrique.salesapicomeia;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salesController")
public class SalesController {
    @GetMapping("/getSales")
    public String getSales() {
        return "Primeira Rota";
    }
    @PostMapping("/getSales")
    public String postSales() {
        return "Segunda Rota";
    }
}
