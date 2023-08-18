package com.jpa.jpa.repository;

import com.jpa.jpa.entity.FreeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FreeBoardRepository extends JpaRepository<FreeBoard,Integer> {
    // 자동으로 아래의 쿼리문을 작성해줌
    // select * from freeboard where title like '%title%' or content like '%content%' limit 0,5;
    Page<FreeBoard> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
    // 자신이 직접 작성하는 법
//    @Query("select board from FreeBoard where idx=?1?")
//    FreeBoard myQuery(int idx);
}

// Entity에 의해 생성된 DB에 접근하는 메서드(ex) findAll()) 들을 사용하기 위한 인터페이스이다. 위에서 엔티티를 선언함으로써 데이터베이스 구조를 만들었다면, 여기에 어떤 값을 넣거나, 넣어진 값을 조회하는 등의 CRUD(Create, Read, Update, Delete)를 해야 쓸모가 있는데, 이것을 어떻게 할 것인지 정의해주는 계층이라고 생각하면 된다.
// JpaRepository를 상속받도록 함으로써 기본적인 동작이 모두 가능해진다! JpaRepository는 어떤 엔티티를 메서드의 대상으로 할지를 다음 키워드로 지정한다. JpaRepository<대상으로 지정할 엔티티, 해당 엔티티의 PK의 타입>.
// extends를 통해서 상속받고나면, 해당 레포지토리의 객체를 이용해서 기본적으로 제공되는 메서드(save(), findAll(), get()) 등을 사용할 수 있게 된다.