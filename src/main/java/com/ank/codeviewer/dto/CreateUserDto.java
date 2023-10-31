package com.ank.codeviewer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto {
    private Integer id;
    private String name;
    private String login;
    private String password;
    private String email;
    private String typeUser;
}
