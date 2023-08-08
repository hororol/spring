package www.kb.member.service;

import www.kb.member.dto.request.IdCheckDTO;
import www.kb.member.dto.request.JoinDTO;

import java.util.Map;

public interface MemberService {
    Map<String, Object> checkLoginId(IdCheckDTO dto);

    Map<String, Object> joinMember(JoinDTO dto);
}
