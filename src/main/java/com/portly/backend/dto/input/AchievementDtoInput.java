package com.portly.backend.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AchievementDtoInput {

    private Boolean isActive;
    private List<String> achievements;
    private String backgroundColour;
    private String textColour;
    private String titleBackgroundColour;
    private String titleTextColour;


}
