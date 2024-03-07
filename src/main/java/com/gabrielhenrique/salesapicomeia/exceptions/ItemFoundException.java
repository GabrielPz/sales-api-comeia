package com.gabrielhenrique.salesapicomeia.exceptions;

public class ItemFoundException extends RuntimeException{
    public ItemFoundException(){
        super("Item já existe");
    }
}
