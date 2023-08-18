package com.jpa.jpa.repository;

import com.jpa.jpa.entity.FreeBoard;
import com.jpa.jpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    // select * from where username = ?1 이 생성됩니다.
    public Member findByUsername(String username);
}