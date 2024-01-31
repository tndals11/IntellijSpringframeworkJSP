package com.codingrecipe.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter // getter 생성
@Setter // setter 생성
@ToString // tostriong 생성
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private int memberAge;
    private String memberMobile;
}
