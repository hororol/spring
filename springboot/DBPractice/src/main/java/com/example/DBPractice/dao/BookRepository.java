package com.example.DBPractice.dao;

import com.example.DBPractice.dto.BookDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public class BookRepository {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public List<BookDTO> doSelect(BookDTO bookDTO) {
        return sqlSessionTemplate.selectList("book.doSelect", bookDTO);
    }

    public BookDTO doSelectOne(BookDTO bookDTO){
        return sqlSessionTemplate.selectOne("book.doSelectOne", bookDTO);
    }
}
// selectOne은 레코드 하나, selectList은 여러 레코드

// List selectList(query_id) : id에 대한 select문을 실행한 후 레코드를 List로 반환합니다.
// List selectList(query_id, '조건') : id에 대한 select문을 실행하면서 조건(쿼리문에서 사용할 인자)를 전달합니다.
// T selectOne(query_id) : id에 대한 select문을 실행한 후 한개의 레코드를 지정한 타입으로 반환합니다.
// T selectOne(query_id, '조건')	: id에 대한 select문을 실행하면서 조건(쿼리문에서 사용할 인자)를 전달합니다.