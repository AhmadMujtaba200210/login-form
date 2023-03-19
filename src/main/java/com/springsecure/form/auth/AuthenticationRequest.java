package com.example.jwt.auth;

import lombok.*;
import org.springframework.stereotype.Service;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String passcode;
}
