package com.portly.backend.dto.input;

import com.portly.backend.entities.enums.VerificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SendOtpDto {

    private String email;

    private VerificationType verificationType;

}
