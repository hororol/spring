package www.kb.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import www.kb.board.dto.response.BoardDTO;
import www.kb.member.dao.MemberDao;
import www.kb.member.dto.request.*;
import www.kb.member.dto.response.MemberDTO;
import www.kb.security.util.LoginUtil;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao dao;

    public Map<String, Object> checkLoginId(IdCheckDTO dto) {
        Map<String, Object> map = new HashMap<>();

        int count = dao.selectMemberCountByLoginId(dto);
        if (count > 0) {
            map.put("isUsable", false);
            map.put("message", "이미 사용 중인 아이디 입니다.");
        } else {
            map.put("isUsable", true);
            map.put("message", "사용 가능한 아이디 입니다.");
        }

        return map;
    }

    @Override
    @Transactional
    public Map<String, Object> joinMember(JoinDTO dto) {
        Map<String, Object> map = new HashMap<>();

        if (!StringUtils.isEmpty(dto.getStrBirthday())) {
            dto.setBirthday(LocalDate.parse(
                    dto.getStrBirthday().substring(0, 4)
                    + "-" + dto.getStrBirthday().substring(4, 6)
                    + "-" + dto.getStrBirthday().substring(6))
            );
        }

        dto.setPassword(PasswordEncoderFactories
                .createDelegatingPasswordEncoder().encode(dto.getPassword()));

        dao.registerMember(dto);

        map.put("isSuccess", true);

        return map;
    }

    @Override
    public MemberDTO getMemberInfo() {
        return dao.getMemberInfoByLoginId(LoginUtil.getLoginId());
    }

    @Override
    @Transactional
    public Map<String, Object> updateMember(UpdateDTO dto) {
        dto.setLoginId(LoginUtil.getLoginId());
        if (!StringUtils.isEmpty(dto.getStrBirthday())) {
            dto.setBirthday(LocalDate.parse(
                    dto.getStrBirthday().substring(0, 4)
                            + "-" + dto.getStrBirthday().substring(4, 6)
                            + "-" + dto.getStrBirthday().substring(6))
            );
        }

        dao.updateMemberInfo(dto);

        Map<String, Object> map = new HashMap<>();
        map.put("message", "회원 정보를 수정하였습니다.");
        map.put("isSuccess", true);
        return map;
    }

    @Override
    public Map<String, Object> findId(FindIdDTO dto) {
        String loginId = dao.findId(dto);

        Map<String, Object> map = new HashMap<>();
        if (loginId == null) {
            map.put("message", "회원 정보를 찾을 수 없습니다.");
            map.put("isSuccess", false);
        } else {
            map.put("loginId", loginId);
            map.put("isSuccess", true);
        }
        return map;
    }

    @Override
    public Map<String, Object> findPassword(FindPasswordDTO dto) {
        String loginId = dao.getLoginIdForFindPassword(dto);
        Map<String, Object> map = new HashMap<>();
        if (loginId == null) {
            map.put("message", "회원 정보를 찾을 수 없습니다.");
        } else {
            map.put("loginId", loginId);
        }

        map.put("isSuccess", true);
        return map;
    }

    @Override
    public Map<String, Object> updatePassword(UpdatePasswordDTO dto) {
        dto.setPassword(PasswordEncoderFactories
                .createDelegatingPasswordEncoder().encode(dto.getPassword()));

        dao.updatePassword(dto);
        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", true);
        map.put("message", "비밀번호를 변경하였습니다.");
        return map;
    }

    @Override
    @Transactional
    public void leave() {
        dao.leave(LoginUtil.getLoginId());
    }
}











