package com.codingrecipe.member.controller;

import com.codingrecipe.member.dto.MemberDTO;
import com.codingrecipe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member") // RequestMapping 공통의 주소를 묶는다
@RequiredArgsConstructor// RequiredArgsConstructor 생성자를 가져오도록
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/save")
    public String saveForm() {

        return "save"; // jsp를 띄어주는
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDto) {
        int saveResult = memberService.save(memberDto);

        if (saveResult > 0) {
            return "login"; // 가입 성공
        } else {
            return "save"; // 가입 실패
        }
    }
}