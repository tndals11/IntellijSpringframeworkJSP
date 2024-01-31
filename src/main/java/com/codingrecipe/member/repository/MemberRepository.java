package com.codingrecipe.member.repository;

import com.codingrecipe.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final SqlSessionTemplate sql; //mybatis에서 제공하는

    public int save(MemberDTO memberDTO) { // db에 들어가는곳
        System.out.println("memberDTO = " + memberDTO);
        // Member => namespace="Member"이 이름으로 들어가고
        // .save Mapper안에 id="save"의 값으로 들어간다
        return sql.insert("Member.save", memberDTO);
    }
}
