package www.kb.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import www.kb.member.dto.request.JoinDTO;
import www.kb.member.dto.response.MemberDTO;
import www.kb.member.dto.request.IdCheckDTO;

@Repository
public class MemberDaoImpl implements MemberDao {
    @Autowired
    private SqlSession session;

    public int selectMemberCountByLoginId(IdCheckDTO dto) {
        return session.selectOne("member.selectMemberCountByLoginId", dto);
    }

    public MemberDTO getLoginInfo(String id) {
        return session.selectOne("member.selectLoginInfoById", id);
    }

    @Override
    public void registerMember(JoinDTO dto) {
        session.insert("member.insertMemberDetail", dto);
        session.insert("member.insertMember", dto);
    }
}








