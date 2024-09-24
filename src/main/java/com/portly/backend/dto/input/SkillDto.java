package com.portly.backend.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SkillDto {

    private Boolean isActive;
    private List<String> advance;
    private List<String> intermediate;
    private List<String> beginner;
    private String backgroundColour;
    private String textColour;
    private String tagBackgroundColour;
    private String tagTextColour;

}
