package com.portly.backend.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EditUserProfileRequestDto {


    private String name;

    private String oldPassword;

    private String newPassword;

}
