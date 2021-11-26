package com.pngabo.belgorent;

import com.pngabo.belgorent.services.LocationServiceImpl;
import com.pngabo.belgorent.services.VoitureService;
import org.springframework.stereotype.Component;

@Component
public class Menu {
    private final VoitureService vService;
    private final LocationServiceImpl lService;

    public Menu(VoitureService vService, LocationServiceImpl lService) {
        this.vService = vService;
        this.lService = lService;
    }

    public void afficherVoitures() {
        System.out.println(vService.getAll());
    }
    public void afficherLocations() {
        System.out.println(lService.getAll());
    }
}
