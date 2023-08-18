package com.example.springboot230703.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class People {
    private int idx;
    private String name;
    private int age;
}
