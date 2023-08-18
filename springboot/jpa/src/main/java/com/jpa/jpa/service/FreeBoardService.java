package com.jpa.jpa.service;

import com.jpa.jpa.dto.FreeBoardDto;
import com.jpa.jpa.entity.FreeBoard;
import com.jpa.jpa.repository.FreeBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // service는 객체등록을 해주어야한다.
public class FreeBoardService {

    @Autowired
    FreeBoardRepository freeBoardRepository;

    public Page<FreeBoard> list(String searchText, String SearchText, Pageable pageable) { // @PageableDefault(size = 5, sort = "idx", direction = Sort.Direction.DESC, page = 0)
        // 데이터베이스에 가서 select 해서 내용을 가지고 온 다음 list에 담는다.
        // 컬렉션과 제네릭(list, Map, Set) 컬렉션은 자료형
        // Page <-> List (page와 list는 동일하다)
//        Pageable pageable = PageRequest.of(0,5, Sort.by("idx").descending());
        Page<FreeBoard> pagelist = freeBoardRepository.findByTitleContainingOrContentContaining(searchText, SearchText, pageable); // PageRequest.of(0,5) : 0페이지에 5개씩 -> 0페이지부터 늘어난다.
        return pagelist;
    }

    public boolean insert(FreeBoardDto dto) {
        FreeBoard freeBoardEntity = freeBoardRepository.findById(dto.getIdx()).orElse(new FreeBoard());
        freeBoardEntity.setContent(dto.getContent());
        freeBoardEntity.setName(dto.getName());
        freeBoardEntity.setTitle(dto.getTitle());
        freeBoardRepository.save(freeBoardEntity);
        return true;
    }

    public FreeBoardDto getRow(FreeBoardDto freeBoardDto) {
        // orElse() : 만약에 결과 행이 없으면
        FreeBoard freeBoard = freeBoardRepository.findById(freeBoardDto.getIdx()).orElse(new FreeBoard());
        return FreeBoardDto.of(freeBoard);
    }
}
