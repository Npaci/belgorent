package com.pngabo.belgorent.models.dtos;

import lombok.Data;

import java.time.Instant;

@Data
public class ErrorDTO {
    private String message;
    private Instant timestamp = Instant.now();

    public ErrorDTO(String message) {
        this.message = message;
    }
}
