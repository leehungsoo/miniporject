package bitc.aws402.miniproject.mapper;

import bitc.aws402.miniproject.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MainMapper {
  public int selectMemberLogin(@Param("memberId")  String memberId, @Param("memberPwd") String memberPwd);
  public MemberDTO selectMemberDetail(@Param("memberId")  String memberId);
}
