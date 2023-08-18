package com.kb.org.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class FreeBoard {
    private int idx;
    private String writer;
    private String content;
    private String title;
}
