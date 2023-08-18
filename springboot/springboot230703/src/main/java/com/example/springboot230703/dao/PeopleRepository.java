package com.example.springboot230703.dao;

import com.example.springboot230703.dto.People;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PeopleRepository {
    @Autowired
    SqlSession sqlSession;

    public List<People> doselect(){
        return sqlSession.selectList("people.select");
    }
}
