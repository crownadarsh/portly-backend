package com.portly.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectDto {

    private List<Integer> image;
    private List<String> tags;
    private String title;
    private String description;
    private String projectUrl;

}
