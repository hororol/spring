package www.kb.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import www.kb.member.dto.response.MemberDTO;
import www.kb.member.service.MemberService;
import www.kb.security.util.LoginUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/member")
public class MemberController {
    @Autowired
    private MemberService service;

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

    // 회원가입 결과 페이지 이동
    @RequestMapping(value = "/join/result", method = RequestMethod.GET)
    public String gotoJoinResult() {
        return "member/joinResult";
    }

    // 로그아웃
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        LoginUtil.logout(request, response);
        return "redirect:/";
    }

    // 마이페이지
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String gotoJoinResult(Model model) {
        MemberDTO dto = service.getMemberInfo();
        model.addAttribute("dto", dto);
        return "member/info";
    }

    // 아이디 찾기
    @RequestMapping(value = "/find/id", method = RequestMethod.GET)
    public String gotoFindIdForm() {
        return "member/findIdForm";
    }

    // 비밀번호 찾기
    @RequestMapping(value = "/find/password", method = RequestMethod.GET)
    public String gotoFindPasswordForm() {
        return "member/findPasswordForm";
    }

    // 비밀번호 변경
    @RequestMapping(value = "/password/update", method = RequestMethod.GET)
    public String gotoPasswordUpdateForm(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String loginId = (String) session.getAttribute("loginId");
        if (loginId == null) {
            throw new RuntimeException("잘못된 접근입니다.");
        }

        return "member/passwordUpdateForm";
    }

    // 회원탈퇴
    @RequestMapping(value = "/leave", method = RequestMethod.GET)
    public String leave(HttpServletRequest request, HttpServletResponse response) {
        service.leave();
        LoginUtil.logout(request, response);
        return "redirect:/";
    }
}













