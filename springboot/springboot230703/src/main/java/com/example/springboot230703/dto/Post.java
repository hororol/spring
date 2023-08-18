package com.example.springboot230703.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor // 기본생성자
@Builder
public class Post {
    private int idx;
    private String content;
}
