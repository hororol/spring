package www.kb.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("serial")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO implements UserDetails {
    private int id;
    private String loginId;
    private String password;
    private LocalDate joinDate;
    private int memberDetailId;
    private String name;
    private String mobileNo;
    private LocalDate birthday;
    private String email;
    private String zipcode;
    private String address;
    private String addressDetail;




    // 시큐리티를 위한 구현 메소드
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority("ROLE_USER"));
        return auth;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.loginId;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
