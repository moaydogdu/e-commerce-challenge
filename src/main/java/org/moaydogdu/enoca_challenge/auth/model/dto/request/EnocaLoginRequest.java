package org.moaydogdu.enoca_challenge.auth.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnocaLoginRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}