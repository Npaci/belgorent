package com.pngabo.belgorent.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VINValidityDTO {
    private boolean valid;
    private String VIN;
}
