package www.kb.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginUtil {
    public static String getLoginId() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (object.equals("anonymousUser")) {
            return null;
        }
        UserDetails userDetails = (UserDetails) object;

        String loginId = userDetails.getUsername();
        return loginId;
    }

    public static void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
    }
}
