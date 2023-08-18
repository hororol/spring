package www.kb.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import www.kb.member.dao.MemberDaoImpl;
import www.kb.member.dto.response.MemberDTO;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private MemberDaoImpl dao;

    // 로그인 관련 정보 로드
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        // 회원 정보 데이터 세팅
        MemberDTO dto = dao.getLoginInfo(id);
        if (dto == null) {
            throw new UsernameNotFoundException(id);
        }
        return dto;
    }
}
