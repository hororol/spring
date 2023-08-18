package www.kb.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import www.kb.member.dto.request.*;
import www.kb.member.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/member")
public class MemberRestController {
    @Autowired
    private MemberService service;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> procExceptionForAjax(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public Map<String, Object> procRuntimeException(Exception e) {
        Map<String, Object> response = new HashMap<>();
        response.put("isSuccess", false);
        response.put("message", e.getMessage());
        return response;
    }

    // 중복아이디 확인
    @RequestMapping(value = "/check/id", method = RequestMethod.POST)
    public Map<String, Object> checkLoginId(@Valid IdCheckDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new RuntimeException("잘못된 접근입니다.");
        }

        Map<String, Object> response = service.checkLoginId(dto);

        return response;
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public Map<String, Object> joinMember(@Valid JoinDTO dto, Errors errors) {
        if (errors.hasErrors() || !dto.getPassword().equals(dto.getPasswordCheck())) {
            throw new RuntimeException("잘못된 접근입니다.");
        }

        Map<String, Object> response = service.joinMember(dto);

        return response;
    }

    @RequestMapping(value = "/info/update", method = RequestMethod.POST)
    public Map<String, Object> updateMember(@Valid UpdateDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new RuntimeException("잘못된 접근입니다.");
        }

        Map<String, Object> response = service.updateMember(dto);

        return response;
    }

    @RequestMapping(value = "/find/id", method = RequestMethod.POST)
    public Map<String, Object> findId(@Valid FindIdDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new RuntimeException("잘못된 접근입니다.");
        }

        Map<String, Object> response = service.findId(dto);

        return response;
    }

    @RequestMapping(value = "/find/password", method = RequestMethod.POST)
    public Map<String, Object> findPassword(HttpServletRequest request
            , @Valid FindPasswordDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new RuntimeException("잘못된 접근입니다.");
        }

        Map<String, Object> response = service.findPassword(dto);
        String loginId = (String) response.get("loginId");
        if (loginId != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginId", loginId);
        }


        return response;
    }

    @RequestMapping(value = "/password/update", method = RequestMethod.POST)
    public Map<String, Object> updatePassword(HttpServletRequest request
            , @Valid UpdatePasswordDTO dto, Errors errors) {
        if (errors.hasErrors() || !dto.getPassword().equals(dto.getPasswordCheck())) {
            throw new RuntimeException("잘못된 접근입니다.");
        }

        HttpSession session = request.getSession();
        String loginId = (String) session.getAttribute("loginId");
        if (loginId == null) {
            throw new RuntimeException("잘못된 접근입니다.");
        } else {
            dto.setLoginId(loginId);
        }

        Map<String, Object> response = service.updatePassword(dto);

        session.removeAttribute("loginId");
        return response;
    }
}






