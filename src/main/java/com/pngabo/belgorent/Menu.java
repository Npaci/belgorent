package com.pngabo.belgorent;

import com.pngabo.belgorent.services.VoitureService;
import org.springframework.stereotype.Component;

@Component
public class Menu {
    private final VoitureService vService;

    public Menu(VoitureService vService) {
        this.vService = vService;
    }

    public void afficherVoitures() {
        System.out.println(vService.getAll());
    }
}
