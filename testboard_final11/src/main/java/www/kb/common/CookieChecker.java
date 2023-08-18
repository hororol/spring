package www.kb.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieChecker {
    public boolean cookieCheck(HttpServletRequest request, HttpServletResponse response, int id, String name){
        boolean countCheck = false;
        Cookie[] cookies = request.getCookies();
        Cookie viewCookie = null;
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                // cookie name이 일치하는 쿠키를 viewCookie 입력
                if (cookies[i].getName().equals(name)) {
                    viewCookie = cookies[i];
                }
            }
        }

        if (viewCookie == null) {   // viewCookie 부재 시 새로운 쿠키 입력
            Cookie newCookie = new Cookie(name, id + "&");
            newCookie.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(newCookie);
            countCheck = true;
        } else {    // viewCookie 존재 시 새로운 쿠키 입력
            String value = viewCookie.getValue();
            // 입력한 번호와 일치하는 번호가 없으면 추가
            if (value.indexOf(id + "&") < 0) {
                value = value + id + "&";
                viewCookie.setValue(value);
                viewCookie.setMaxAge(60 * 60 * 24 * 365);
                response.addCookie(viewCookie);
                countCheck = true;
            }
        }
        return countCheck;
    }
}