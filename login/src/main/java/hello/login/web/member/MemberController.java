package hello.login.web.member;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }
        memberRepository.save(member);
        return "redirect:/";
    }

    @GetMapping("/admin") // 관리자 추가
    public String addadminForm(@ModelAttribute("member") Member member, Model model) {

        List<Member> memberList = memberRepository.findAll();
        model.addAttribute("memberList", memberList);

        return "members/addAdminForm";
    }

    @PostMapping("/admin") //관리자 추가
    public String addAdmin(@Valid @ModelAttribute Member member, BindingResult bindingResult, Model model) {

        //1. 기본 유효성 검증
        if (bindingResult.hasErrors()) {
            return "members/addAdminForm"; // 유효성 검사 실패 시 다시 폼으로 이도
        }

        // 2. 데이터베이스에서 회원 조회
        Optional<Member> existinMember = memberRepository.findByLoginId(member.getLoginId());
        if(existinMember.isEmpty()) {
            model.addAttribute("message", "선택한 회원이 존재하지 않습니다.");
            return "members/addAdminForm";
        }

        Member dbMember = existinMember.get();
        if("A".equals(dbMember.getRole())) {
            model.addAttribute("message", "이미 관리자로 등록된 회원입니다.");
            return "members/addAdminForm";
        }

        dbMember.setRole("A");
        memberRepository.save(dbMember);

        model.addAttribute("message", "관리자가 성공적으로 추가되었습니다.");
        return "members/addAdminForm";
    }




}
