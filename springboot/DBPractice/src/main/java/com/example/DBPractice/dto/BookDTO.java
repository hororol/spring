package com.example.DBPractice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDTO {
    private int idx;
    private String title;
    private String category;
    private int price;
    private String img;
}
