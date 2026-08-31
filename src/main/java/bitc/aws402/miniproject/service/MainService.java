package bitc.aws402.miniproject.service;

import bitc.aws402.miniproject.dto.MemberDTO;
import bitc.aws402.miniproject.mapper.MainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MainService {

  private final MainMapper mainMapper;

  public int selectMemberLogin(String memberId, String memberPwd){
    int result = mainMapper.selectMemberLogin(memberId, memberPwd);
    return result;
  }

  public MemberDTO selectMemberDetail(String memberId) {
    MemberDTO member = mainMapper.selectMemberDetail(memberId);
    return member;
  }
}
