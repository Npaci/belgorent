package com.pngabo.belgorent.model;

public enum TypeVoiture {
    STANDARD("Standard"),
    SUV("SUV"),
    UTILITAIRE("Utilitaire"),
    MONOSPACE("Monospace");

    public String type;

    TypeVoiture(String type) {
        this.type = type;
    }
}
