package com.pngabo.belgorent.utils;

import com.pngabo.belgorent.repositories.VoitureRepository;
import org.springframework.beans.factory.InitializingBean;

public class DatabaseFiller implements InitializingBean {
    private final VoitureRepository vRepository;

    public DatabaseFiller(VoitureRepository vRepository) {
        this.vRepository = vRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Test DB: "+vRepository.findAll().toString());
    }
}
