package com.jpa.jpa.dto;

import com.jpa.jpa.entity.FreeBoard;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class FreeBoardDto {

    private int idx;
    private String name;

    @Size(min = 3, max = 30, message = "3이상")
    private String title;
    @Size(min = 3, max = 30)
    private String content;

    private LocalDateTime createdDate;

    private static ModelMapper modelMapper = new ModelMapper();

    public FreeBoard createFreeBoard(){
        return modelMapper.map(this, FreeBoard.class);
    }

    public static FreeBoardDto of(FreeBoard freeBoard){
        return modelMapper.map( freeBoard, FreeBoardDto.class);
    }
}
// modelMapper를 쓰는 경우도 있고 Bulider를 쓰는 경우도 있다.