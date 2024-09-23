package com.portly.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppBarDto {

    private Boolean isActive;
    private String backgroundColour;
    private String textColour;

}
