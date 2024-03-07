package com.gabrielhenrique.salesapicomeia.exceptions;

public class SaleFoundException extends RuntimeException{
    public SaleFoundException(){
        super("Venda já existe");
    }
}
