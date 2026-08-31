package bitc.aws402.miniproject.service;

import bitc.aws402.miniproject.dto.AccommodationDTO;
import bitc.aws402.miniproject.dto.MemberDTO;
import bitc.aws402.miniproject.dto.QnaDTO;
import bitc.aws402.miniproject.dto.ReservationDTO;
import bitc.aws402.miniproject.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

//  추형호 -----
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberMapper memberMapper;

    // --- 회원 관련 ---
    public void registerMember(MemberDTO member) throws Exception {
        memberMapper.insertMember(member);
    }

    public MemberDTO findMemberById(String memberId){
        MemberDTO member = memberMapper.selectMemberByUserId(memberId);
        return member;
    }

    public List<MemberDTO> getAllMembers() throws Exception {
        List<MemberDTO> list = memberMapper.selectAllMembers();
        return list != null ? list : Collections.emptyList();
    }

    // [추가] 마이페이지 회원 정보 조회 구현
    public MemberDTO getUserInfo(String userId) {
        return memberMapper.selectUserInfo(userId);
    }

    // [추가] 회원 정보 수정 구현
    public void updateUserInfo(MemberDTO user) {
        memberMapper.updateUserInfo(user);
    }

    // --- Q&A 관련 ---
    public void createQna(QnaDTO qna) throws Exception {
        memberMapper.insertQna(qna);
    }

    public List<QnaDTO> getAllQna() throws Exception {
        return memberMapper.selectAllQna();
    }

    public QnaDTO getQnaById(Long id) throws Exception {
        return memberMapper.selectQnaById(id);
    }

    public void replyQna(Long id, String answer) throws Exception {
        memberMapper.updateQnaAnswer(id, answer);
    }

    public List<QnaDTO> selectAllQna() {
        return memberMapper.selectAllQna();
    }

    public void insertQna(QnaDTO qna) {
        try {
            memberMapper.insertQna(qna);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public QnaDTO getQnaById(long id) {
        return memberMapper.selectQnaById(id);
    }

    public void updateQna(QnaDTO qna) {
        memberMapper.updateQna(qna);
    }

    public void deleteQna(long id) {
        memberMapper.deleteQna(id);
    }

    // --- 숙소 및 예약 관련 ---
    public List<AccommodationDTO> getAllAccommodations() throws Exception {
        List<AccommodationDTO> list = memberMapper.selectAllAccommodations();
        return list != null ? list : Collections.emptyList();
    }

    public List<ReservationDTO> getAllReservations() throws Exception {
        List<ReservationDTO> list = memberMapper.selectAllReservations();
        return list != null ? list : Collections.emptyList();
    }
}
