package com.pngabo.belgorent.exceptions;

public class NotValidVINException extends RuntimeException{
    public NotValidVINException() {
        super("VIN invalid. Il doit être composé de 17 caractères alphanumériques.");
    }
}
