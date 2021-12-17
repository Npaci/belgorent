package com.pngabo.belgorent.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginSuccessDTO {
    private String username;
    private String jwt;
}
