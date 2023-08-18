package com.example.springboot2.obj;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 모든 매개변수를 이용하여 모든 경우의 생성자를 생성한다.
@ToString
public class AA {
    private String name;
}
