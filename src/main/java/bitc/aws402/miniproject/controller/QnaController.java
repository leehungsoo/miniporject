package bitc.aws402.miniproject.controller;

import bitc.aws402.miniproject.dto.MemberDTO;
import bitc.aws402.miniproject.dto.QnaDTO;
import bitc.aws402.miniproject.service.MemberService;
import bitc.aws402.miniproject.service.QnaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//  추형호 -----
@RequestMapping("/qna")
@RequiredArgsConstructor
@Controller
public class QnaController {

    private final MemberService memberService;

    private final QnaService qnaService;

    // Q&A 목록
    @GetMapping("/list")
    public String selectQnaList(Model model) throws Exception {
        List<QnaDTO> list = qnaService.selectQnaList();
        model.addAttribute("list", list);
        return "qna/qnaList";
    }

    // Q&A 작성 폼 (비로그인 시 메시지 전달 후 로그인 페이지로 이동)
    @GetMapping("/write")
    public String qnaWriteView(HttpSession session, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            redirectAttributes.addFlashAttribute("error", "로그인하고 작성해주세요.");
            // 현재 페이지 주소를 redirectURL로 함께 전달
            return "redirect:/member/login?redirectURL=/qna/write";
        }
        return "qna/qnaWrite";
    }

    // Q&A 작성 처리
    @PostMapping("/write")
    public String qnaWriteProcess(QnaDTO qna, HttpSession session) throws Exception {
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/member/login";
        }

        // 작성자에 로그인한 사용자의 ID 또는 이름을 지정 (DB 설계에 맞춰 설정, 여기선 userName으로 예시)
        qna.setBoardCreateIdx(loginUser.getMemberIdx());
        qnaService.insertQna(qna);
        return "redirect:/qna/list";
    }

    // Q&A 상세 조회
    @GetMapping("/detail")
    public String selectQnaDetail(@RequestParam("id") int id, Model model) throws Exception {
        qnaService.updateHitCount(id);
        QnaDTO qna = qnaService.selectQnaDetail(id);
        if (qna == null) {
            return "redirect:/qna/list";
        }
        model.addAttribute("qna", qna);
        return "qna/qnaDetail";
    }

    // Q&A 수정 폼 (작성자 본인 확인)
    @GetMapping("/edit")
    public String qnaEditForm(@RequestParam("id") int id, HttpSession session, Model model) throws Exception {
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/member/login?redirectURL=/qna/detail?id=" + id;
        }

        QnaDTO qna = qnaService.selectQnaDetail(id);
        if (qna == null) {
            return "redirect:/qna/list";
        }

        // 작성자 본인 검증 (DB의 writer가 이름인지 아이디인지 확인 후 비교)
        if (qna.getBoardCreateIdx() != loginUser.getMemberIdx()) {
            return "redirect:/qna/detail?id=" + id;
        }

        model.addAttribute("qna", qna);
        return "qna/qnaEdit";
    }

    // Q&A 수정 처리
    @PostMapping("/edit")
    public String qnaUpdateProcess(QnaDTO qna, HttpSession session) throws Exception {
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/member/login";
        }

        QnaDTO originQna = qnaService.selectQnaDetail(qna.getBoardIdx());
        if (originQna == null || qna.getBoardCreateIdx() != loginUser.getMemberIdx()) {
            return "redirect:/qna/list";
        }

        qnaService.updateQna(qna);
        return "redirect:/qna/detail?id=" + qna.getBoardIdx();
    }

    // Q&A 삭제 처리 (작성자 본인 확인)
    @GetMapping("/delete")
    public String qnaDeleteProcess(@RequestParam("id") int id, HttpSession session) throws Exception {
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/member/login";
        }

        QnaDTO qna = qnaService.selectQnaDetail(id);
        if (qna != null && qna.getBoardCreateIdx() == loginUser.getMemberIdx()) {
            qnaService.deleteQna(id);
        }
        return "redirect:/qna/list";
    }

    // 관리자 답변 등록
    @PostMapping("/admin/answer")
    public String insertQnaAnswer(QnaDTO qna) throws Exception {
        qnaService.insertQnaAnswer(qna);
        return "redirect:/qna/detail?id=" + qna.getBoardIdx();
    }
}
