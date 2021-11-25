package com.pngabo.belgorent.model;

public enum Carburant {
    DIESEL("Diesel"),
    ESSENCE("Essence"),
    LPG("LPG");

    public String carburant;

    Carburant(String carburant) {
        this.carburant = carburant;
    }
}
