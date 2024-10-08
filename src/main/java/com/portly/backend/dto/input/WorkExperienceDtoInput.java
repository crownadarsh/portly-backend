package com.portly.backend.dto.input;

import com.portly.backend.dto.WorkDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkExperienceDtoInput {

    private Boolean isActive;
    private String backgroundColour;
    private String textColour;
    private String cardBackgroundColour;
    private String cardTextColour;
    private List<WorkDto> workList;

}
