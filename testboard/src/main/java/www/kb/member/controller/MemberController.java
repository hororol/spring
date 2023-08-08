package www.kb.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/member")
public class MemberController {

    @ExceptionHandler(Exception.class)
    public String procException() {
        return "redirect:/error";
    }

    @ExceptionHandler(RuntimeException.class)
    public String procRuntimeException(HttpServletRequest request, Exception e) {
        request.setAttribute("message", e.getMessage());
        return "alert";
    }

    // 세션 만료
    @RequestMapping(value = "/session/expire", method = RequestMethod.GET)
    public String expireSession() {
        return "member/session";
    }

    // 로그인 폼 이동
    @RequestMapping(value = "/login")
    public String gotoLoginForm() {
        return "member/loginForm";
    }

    // 회원가입 폼 이동
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String gotoJoinForm() {
        return "member/joinForm";
    }
}
