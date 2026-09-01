package bitc.aws402.miniproject.dto;

import lombok.Data;

//  추형호 -----
@Data
public class QnaDTO {
    private int boardIdx;
    private String boardTitle;
    private String boardContents;
    private int boardCreateIdx;
    private String boardCreateDate;
    private int boardHitCnt;
    private String memberName;
}
