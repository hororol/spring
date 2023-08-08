package www.kb.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import www.kb.member.dao.MemberDao;
import www.kb.member.dto.request.IdCheckDTO;
import www.kb.member.dto.request.JoinDTO;

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
}
