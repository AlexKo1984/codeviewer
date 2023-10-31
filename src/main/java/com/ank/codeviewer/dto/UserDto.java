package com.ank.codeviewer.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String name;
    private String login;
    private String email;
    private String typeUser;
}
