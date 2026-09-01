package bitc.aws402.miniproject.mapper;

import bitc.aws402.miniproject.dto.QnaDTO;
import bitc.aws402.miniproject.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//  추형호 -----
@Mapper
public interface QnaMapper {
    List<QnaDTO> selectQnaList() ;
    void insertQna(QnaDTO qna);
    QnaDTO selectQnaDetail(int qnaIdx);
    ReplyDTO selectQnaReply(@Param("boardIdx") int boardIdx);
    void updateHitCount(int qnaIdx);
    void updateQna(QnaDTO qna);
    void deleteQna(int qnaIdx);
    void insertQnaAnswer(QnaDTO qna);
}
