package www.kb.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import www.kb.member.dto.response.MemberDTO;

@Slf4j
public class AuthenticationProviderImpl implements AuthenticationProvider {
    @Autowired
    private UserDetailsService svc;

    @SuppressWarnings("unchecked")
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginId = (String) authentication.getPrincipal();
        String loginPw = (String) authentication.getCredentials();

        MemberDTO dto = (MemberDTO) svc.loadUserByUsername(loginId);

        if (!PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(loginPw, dto.getPassword())) {
            throw new BadCredentialsException("");
        }

        return new UsernamePasswordAuthenticationToken(dto, loginPw, dto.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
