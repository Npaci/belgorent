package com.pngabo.belgorent.exceptions;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException() {
        super("Cet element n'existe pas!");
    }
}
