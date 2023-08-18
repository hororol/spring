package www.kb.security.handler;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Data
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    private String idParam;
    private String defaultUrl;

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 로그인 실패 에러 세션 삭제
        clearAuthenticationAttributes(request);

        // 로그인 후 경로 설정
        resultRedirectStrategy(request, response);
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    private void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        // 로그인 정보 불러오기
        String targetUrl = "";

        if (savedRequest != null) {
            targetUrl = savedRequest.getRedirectUrl();

            redirectStrategy.sendRedirect(request, response, targetUrl);
        } else {
            HttpSession session = request.getSession();
            if (session != null) {
                targetUrl = (String) session.getAttribute("prevPage");
                if (targetUrl != null) {
                    session.removeAttribute("prevPage");
                    getRedirectStrategy().sendRedirect(request, response, targetUrl);
                } else {
                    redirectStrategy.sendRedirect(request, response, defaultUrl);
                }
            } else {
                redirectStrategy.sendRedirect(request, response, defaultUrl);
            }
        }

    }
}
