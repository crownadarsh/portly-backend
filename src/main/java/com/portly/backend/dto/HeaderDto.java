package com.portly.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HeaderDto {
    private Boolean isActive;
    private String heroDescription;
    private String image;
    private List<Integer> cv;
    private String backgroundColour;
    private String textColour;
}
