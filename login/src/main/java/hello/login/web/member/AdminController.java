package hello.login.web.member;


import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    private final MemberRepository memberRepository;

    public AdminController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/admin/list")
    public String adminList(Model model) {
        // 모든 관리자 회원 조회 (예시: role이 "A"인 사용자만 필터링)
        List<Member> adminMembers = (List<Member>) memberRepository.findByRole("A");
        model.addAttribute("admins", adminMembers);

        return "ok";
    }

}
