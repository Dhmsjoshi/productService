package dev.dharam.productservice.client.authenticationClient.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenResponseDto {
    private UserResponseDto userResponseDto;
    private SessionStatus sessionStatus;

}
