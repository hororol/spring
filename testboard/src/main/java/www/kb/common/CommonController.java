package www.kb.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class CommonController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String gotoHome() {
        return "home";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String gotoError() {
        return "error";
    }
}
