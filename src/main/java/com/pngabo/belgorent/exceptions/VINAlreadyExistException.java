package com.pngabo.belgorent.exceptions;

public class VINAlreadyExistException extends RuntimeException{
    public VINAlreadyExistException() {
        super("Ce numéro VIN existe déjà!");
    }
}
