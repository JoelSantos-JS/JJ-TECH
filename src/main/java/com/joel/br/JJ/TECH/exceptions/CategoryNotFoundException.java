package com.joel.br.JJ.TECH.exceptions;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException() {
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
