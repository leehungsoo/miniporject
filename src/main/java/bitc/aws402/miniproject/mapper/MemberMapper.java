package bitc.aws402.miniproject.mapper;

import bitc.aws402.miniproject.dto.AccommodationDTO;
import bitc.aws402.miniproject.dto.MemberDTO;
import bitc.aws402.miniproject.dto.QnaDTO;
import bitc.aws402.miniproject.dto.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//  추형호 -----
@Mapper
public interface MemberMapper {
    void insertMember(MemberDTO member) throws Exception;
    MemberDTO selectMemberByUserId(@Param("memberId") String memberId);
    List<MemberDTO> selectAllMembers() throws Exception;

    // --- [추가] 마이페이지 및 정보 수정 ---
    MemberDTO selectUserInfo(String userId);
    void updateUserInfo(MemberDTO user);

    // QnA 관련
    QnaDTO selectQnaById(Long id) throws Exception;
    void updateQnaAnswer(@Param("id") Long id, @Param("answer") String answer) throws Exception;

    // Q&A 전체 목록 조회
    List<QnaDTO> selectAllQna();

    // Q&A 등록
    void insertQna(QnaDTO qna);

    // Q&A 상세 조회 (id: long)
    QnaDTO selectQnaById(long id);

    // Q&A 수정
    void updateQna(QnaDTO qna);

    // Q&A 삭제 (id: long)
    void deleteQna(long id);

    // 관리자 조회용
    List<AccommodationDTO> selectAllAccommodations() throws Exception;
    List<ReservationDTO> selectAllReservations() throws Exception;
}
