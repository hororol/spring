package com.jpa.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class Member extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    private String username;
    private String password;

    private String email;
    private String age;
    private String gender;

    @ManyToMany
    @JoinTable(name = "member_role", joinColumns = @JoinColumn(name = "member_idx"), inverseJoinColumns = @JoinColumn(name = "role_idx"))
    private List<Role> roles;

    // @JoinTable : member_role이라는 중간 다리 테이블을 생성한다.
    // joinColumns : 조인할 컬럼
    // inverseJoinColumns : 참조할 컬럼

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<FreeBoard> boards = new ArrayList<>();

}
