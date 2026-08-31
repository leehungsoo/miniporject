package bitc.aws402.miniproject.controller;

import bitc.aws402.miniproject.dto.MemberDTO;
import bitc.aws402.miniproject.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//  추형호 -----
@RequestMapping("/admin")
@RequiredArgsConstructor
@Controller
public class AdminController {
    private final MemberService memberService;

    @GetMapping({"", "/", "/index"})
    public String adminIndex() {
        // templates/admin/index.html 파일이 존재한다고 가정
        return "admin/login";
    }

    @GetMapping("/login")
    public String adminLoginForm() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String adminLoginProcess(@RequestParam("memberId") String memberId, // 파라미터 이름 수정
                                    @RequestParam("memberPwd") String memberPwd, // 파라미터 이름 수정
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) {
        try {
            MemberDTO member = memberService.findMemberById(memberId);

            if (member != null && member.getMemberLevel() == 99 && memberPwd.equals(member.getMemberPwd())) {
                session.setAttribute("adminUser", member);
                return "redirect:/admin/dashboard";
            } else {
                redirectAttributes.addFlashAttribute("error", "아이디와 비번이 일치하지 않거나 관리자 권한이 없습니다.");
                return "redirect:/admin/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "로그인 처리 중 오류가 발생했습니다.");
            return "redirect:/admin/login";
        }
    }

//    mybatis 로 변경 필요
    @GetMapping("/dashboard")
    public String dashboard(Model model) throws Exception {
//        List<MemberDTO> members = memberService.getAllMembers();
//        List<AccommodationDTO> accommodations = memberService.getAllAccommodations();
//        List<Qna> qnaList = qnaRepository.findAllByOrderByIdDesc(); // Q&A 목록 조회
//
//        model.addAttribute("members", members);
//        model.addAttribute("accommodations", accommodations);
//        model.addAttribute("qnaList", qnaList); // 뷰로 전달

        return "admin/dashboard";
    }

    @GetMapping("/logout")
    public String adminLogout(HttpSession session) {
        session.removeAttribute("adminUser");
        session.invalidate(); // 세션 완전 무효화
        return "redirect:/admin/login";
    }

    // 1. 관리자 메인 페이지 (회원 목록 및 Q&A 목록 조회)
//    mybatis 로 변경 필요
    @GetMapping("")
    public String adminMain(Model model) {
//        model.addAttribute("userList", userRepository.findAll());
//        model.addAttribute("qnaList", qnaRepository.findAllByOrderByIdDesc());
        return "admin/main";
    }

    // 2. Q&A 상세보기
//    mybatis 로 변경 필요
    @GetMapping("/qna/{id}")
    public String adminQnaDetail(@PathVariable("id") Long id, Model model) {
//        Qna qna = qnaRepository.findById(id).orElseThrow();
//        model.addAttribute("qna", qna);
        return "admin/qnaDetail";
    }

    // 3. 관리자 답글 등록 처리 (수정 완료 후 관리자 대시보드로 이동)
//    mybatis 로 변경 필요
    @PostMapping("/qna/reply/{id}")
    public String adminQnaReply(@PathVariable("id") Long id, @RequestParam("reply") String reply) {
//        Qna qna = qnaRepository.findById(id).orElseThrow();
//        qna.setReply(reply);
//        qna.setReplyCreatedAt(LocalDateTime.now());
//        qna.setReplied(String.valueOf(true));
//        qnaRepository.save(qna);

        // 답변 등록 후 대시보드(관리자 페이지)로 이동
        return "redirect:/admin/dashboard";
    }

    // 회원 / 숙소 목록 페이지 이동
    @GetMapping("/list")
    public String adminList(Model model) {
        return "admin/list";
    }
}
