package com.pngabo.belgorent.exceptions;

public class UsernameAlreadyExist extends RuntimeException{
    public UsernameAlreadyExist() {
        super("Cet identifiant existe déjà");
    }
}
