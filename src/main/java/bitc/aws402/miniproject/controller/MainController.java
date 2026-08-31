package bitc.aws402.miniproject.controller;

import bitc.aws402.miniproject.dto.MemberDTO;
import bitc.aws402.miniproject.service.MainService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final MainService mainService;

    // http://localhost:8080/ 및 http://localhost:8080/index 둘 다 접속 가능
    @GetMapping({"", "/", "/index"})
    public String index() {
        return "index"; // templates/index.html 파일을 리턴
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("memberId") String memberId, @RequestParam("memberPwd") String memberPwd, HttpSession session, Model model) {
        int loginResult = mainService.selectMemberLogin(memberId, memberPwd);
        if(loginResult >= 0){
//        로그인 성공 시 session에 회원 정보를 저장하고 원래 있던 페이지로 이동
            MemberDTO member = mainService.selectMemberDetail(memberId);
            session.setAttribute("memberIdx", member.getMemberIdx());
            session.setAttribute("memberId", member.getMemberId());
            session.setAttribute("memberName", member.getMemberName());
            session.setAttribute("memberPhone", member.getMemberPhone());
            session.setAttribute("memberGender", member.getMemberGender());
            session.setAttribute("memberEmail", member.getMemberEmail());
            session.setAttribute("memberJoinDate", member.getMemberJoinDate());
            session.setAttribute("memberLevel", member.getMemberLevel());
            session.setAttribute("memberStatus", member.getMemberStatus());
            session.setMaxInactiveInterval(60*30);  //세션 유지 시간 30분
            return "redirect:/";
        }else{
//            로그인 실패시 alert 창을 띄우고 로그인 페이지로 이동
            return alert("로그인에 실패했습니다.", "/login", model);
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("memberIdx");
        session.removeAttribute("memberId");
        session.removeAttribute("memberName");
        session.removeAttribute("memberPhone");
        session.removeAttribute("memberGender");
        session.removeAttribute("memberEmail");
        session.removeAttribute("memberJoinDate");
        session.removeAttribute("memberLevel");
        session.removeAttribute("memberStatus");
        session.invalidate();
        return "redirect:/";
    }


//    alert 창 출력을 위한 메소드
    private String alert(String msg, String url, Model model){
        model.addAttribute("msg", msg);
        model.addAttribute("url", url);
        return "common/alert";
    }
}