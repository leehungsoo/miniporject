package bitc.aws402.miniproject.service;

import bitc.aws402.miniproject.dto.QnaDTO;
import bitc.aws402.miniproject.mapper.QnaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//  추형호 -----
@RequiredArgsConstructor
@Service
public class QnaService {

    private final QnaMapper qnaMapper;

    public List<QnaDTO> selectQnaList() throws Exception {
        return qnaMapper.selectQnaList();
    }

    public void insertQna(QnaDTO qna) throws Exception {
        qnaMapper.insertQna(qna);
    }

    public QnaDTO selectQnaDetail(int qnaIdx) throws Exception {
        return qnaMapper.selectQnaDetail(qnaIdx);
    }

    public void updateHitCount(int qnaIdx) throws Exception {
        qnaMapper.updateHitCount(qnaIdx);
    }

    public void updateQna(QnaDTO qna) throws Exception {
        qnaMapper.updateQna(qna);
    }

    public void deleteQna(int qnaIdx) throws Exception {
        qnaMapper.deleteQna(qnaIdx);
    }

    public void insertQnaAnswer(QnaDTO qna) throws Exception {
        qnaMapper.insertQnaAnswer(qna);
    }
}
