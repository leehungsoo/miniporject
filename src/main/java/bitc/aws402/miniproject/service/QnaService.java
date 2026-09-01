package bitc.aws402.miniproject.service;

import bitc.aws402.miniproject.dto.QnaDTO;
import bitc.aws402.miniproject.dto.ReplyDTO;
import bitc.aws402.miniproject.mapper.QnaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//  추형호 -----
@RequiredArgsConstructor
@Service
public class QnaService {

    private final QnaMapper qnaMapper;

    public List<QnaDTO> selectQnaList() {
        return qnaMapper.selectQnaList();
    }

    public void insertQna(QnaDTO qna)  {
        qnaMapper.insertQna(qna);
    }

    public QnaDTO selectQnaDetail(int qnaIdx) {
        return qnaMapper.selectQnaDetail(qnaIdx);
    }

    public ReplyDTO selectQnaReply(int boardIdx){
        return qnaMapper.selectQnaReply(boardIdx);
    }

    public void updateHitCount(int qnaIdx) {
        qnaMapper.updateHitCount(qnaIdx);
    }

    public void updateQna(QnaDTO qna) {
        qnaMapper.updateQna(qna);
    }

    public void deleteQna(int qnaIdx) {
        qnaMapper.deleteQna(qnaIdx);
    }

    public void insertQnaAnswer(QnaDTO qna) {
        qnaMapper.insertQnaAnswer(qna);
    }
}
