package com.portly.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkDto {

    private String companyName;
    private String duration;
    private String jobDescription;
    private String jobRole;

}
