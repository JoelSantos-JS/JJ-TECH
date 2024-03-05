package com.joel.br.JJ.TECH.exceptions;

public class ItemOrderException extends RuntimeException{

    public ItemOrderException() {
        super();
    }

    public ItemOrderException(String message) {
        super(message);
    }
}
