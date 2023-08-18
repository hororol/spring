package www.kb.member.service;

import www.kb.member.dto.request.*;
import www.kb.member.dto.response.MemberDTO;

import java.util.Map;

public interface MemberService {
    Map<String, Object> checkLoginId(IdCheckDTO dto);

    Map<String, Object> joinMember(JoinDTO dto);

    MemberDTO getMemberInfo();

    Map<String, Object> updateMember(UpdateDTO dto);

    Map<String, Object> findId(FindIdDTO dto);

    Map<String, Object> findPassword(FindPasswordDTO dto);

    Map<String, Object> updatePassword(UpdatePasswordDTO dto);

    void leave();
}




