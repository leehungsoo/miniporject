package bitc.aws402.miniproject.service;

import bitc.aws402.miniproject.dto.QnaDTO;
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

    public QnaDTO selectQnaDetail(int id) {
        return qnaMapper.selectQnaDetail(id);
    }

    public void updateHitCount(int id) {
        qnaMapper.updateHitCount(id);
    }

    public void updateQna(QnaDTO qna) {
        qnaMapper.updateQna(qna);
    }

    public void deleteQna(int id) {
        qnaMapper.deleteQna(id);
    }

    public void insertQnaAnswer(QnaDTO qna) {
        qnaMapper.insertQnaAnswer(qna);
    }
}
