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
import www.kb.member.dto.request.IdCheckDTO;
import www.kb.member.dto.request.JoinDTO;
import www.kb.member.service.MemberService;

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
}
