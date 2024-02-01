package com.codingrecipe.member.controller;

import com.codingrecipe.member.dto.MemberDTO;
import com.codingrecipe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    public String save(@ModelAttribute MemberDTO memberDTO) {
        int saveResult = memberService.save(memberDTO);

        if (saveResult > 0) {
            return "login"; // 가입 성공
        } else {
            return "save"; // 가입 실패
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginForm(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
      boolean result = memberService.login(memberDTO);

      if (result) {
          session.setAttribute("loginEmail", memberDTO.getMemberEmail());
          return "main";
      } else {
          return "login";
      }

    }
    @GetMapping("/main")
    public String main() {

        return "main";
        }

    @GetMapping("/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();

        model.addAttribute("memberList", memberDTOList);
        return "list";
    }

    @GetMapping
    public String findById(@RequestParam("id") Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);

        model.addAttribute("member", memberDTO);

        return "detail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {

        memberService.delete(id);
        return "redirect:/member/";
    }

    @GetMapping("/update")
    public String update(Model model, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("member", memberDTO);

        return "update";
    }

    @PostMapping("/update")
    public String updateForm(@ModelAttribute MemberDTO memberDTO) {
        boolean result = memberService.update(memberDTO);

        if (result) {
            return "redirect:/member?id=" + memberDTO.getId();
        } else {
            return "index";
        }
    }

    @PostMapping("/email-check")
    @ResponseBody
    public String emailCheck(@RequestParam("memberEmail") String memberEmail) {
        System.out.println(memberEmail);
        String checkResult = memberService.checkEmail(memberEmail);

        return checkResult;
    }
}