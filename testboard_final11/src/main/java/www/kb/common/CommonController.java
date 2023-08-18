package www.kb.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import www.kb.member.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class CommonController {
    @Autowired
    private MemberService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String gotoHome(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("loginId");
        return "home";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String gotoError() {
        return "error";
    }

    @RequestMapping(value = "/access/deny", method = RequestMethod.GET)
    public String gotoDeny() {
        return "deny";
    }
}
