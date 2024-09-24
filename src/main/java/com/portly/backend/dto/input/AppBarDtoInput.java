package com.portly.backend.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppBarDtoInput {

    private Boolean isActive;
    private String backgroundColour;
    private String textColour;

}
