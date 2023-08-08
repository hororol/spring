package www.kb.member.dao;

import www.kb.member.dto.request.IdCheckDTO;
import www.kb.member.dto.request.JoinDTO;
import www.kb.member.dto.response.MemberDTO;

public interface MemberDao {
    int selectMemberCountByLoginId(IdCheckDTO dto);

    MemberDTO getLoginInfo(String id);

    void registerMember(JoinDTO dto);
}
