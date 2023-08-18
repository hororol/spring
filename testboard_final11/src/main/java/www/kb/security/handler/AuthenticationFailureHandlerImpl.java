package www.kb.security.handler;

import lombok.Data;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    private String idParam;
    private String pwParam;
    private String msg;
    private String url;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        String id = request.getParameter(idParam);
        String pw = request.getParameter(pwParam);
        String failMsg = getFailureMessage(exception);

        request.setAttribute(idParam, id);
        request.setAttribute(pwParam, pw);
        request.setAttribute(msg, failMsg);

        request.getRequestDispatcher(url).forward(request, response);
    }

    private String getFailureMessage(AuthenticationException exception) {
        return "아이디 또는 비밀번호를 확인해 주세요.";
    }
}
