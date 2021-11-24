package com.pngabo.belgorent.model;

public enum EtatVoiture {
    PRET("Prêt"),
    LOUE("Loué"),
    PREPARATION("En Préparation"),
    REPARATION("En Réparation");

    public String etat;
    EtatVoiture(String etat) {
        this.etat = etat;
    }
}
