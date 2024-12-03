package com.sonusardar.dto;


import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class LoginDto {
    private String username;
    private String password;
}
