package com.example.lesson22.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"name", "color"})
public class Cat {

    private String name;
    private int age;
    private String color;
}
