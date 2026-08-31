package bitc.aws402.miniproject.mapper;

import bitc.aws402.miniproject.dto.QnaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//  추형호 -----
@Mapper
public interface QnaMapper {
    List<QnaDTO> selectQnaList() ;
    void insertQna(QnaDTO qna);
    QnaDTO selectQnaDetail(int qnaIdx);
    void updateHitCount(int qnaIdx);
    void updateQna(QnaDTO qna);
    void deleteQna(int qnaIdx);
    void insertQnaAnswer(QnaDTO qna);
}
