package com.jpa.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    // 자동증가하는 컬럼이 기본키가 된다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // primary key
    private int idx;

    // User, Admin, Manager
    private String name;

    public Role(int idx, String name) {
        this.idx = idx;
        this.name = name;
    }

    // EAGER left 바로 하기 때문에
    // 조회할때 데이터를 더 많이 조회 해야 하고
    // LAZY 하게 되면
    // 필요할때 select 구문을 한번더 실행하기 때문에
    // 자원을 한꺼번에 많이 먹는 것은 EAGER
    // 자원을 천천히 먹게 되는 것은 LAZY
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<Member> members;
}
