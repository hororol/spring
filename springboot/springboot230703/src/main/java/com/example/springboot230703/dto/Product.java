package com.example.springboot230703.dto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int idx;
    private String name;
    private int price;
    private int quantity;
}
