package com.example.lesson22.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "password")
public class RegistrationForm {

    private String login;
    private String password;
    private Integer age;

}
