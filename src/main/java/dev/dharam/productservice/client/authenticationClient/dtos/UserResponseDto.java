package dev.dharam.productservice.client.authenticationClient.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class UserResponseDto {
    private UUID id;
    private String email;
    private Set<Role> roles;


}
