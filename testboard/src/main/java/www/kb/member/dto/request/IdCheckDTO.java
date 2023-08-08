package www.kb.member.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

import static www.kb.common.RegexpConstants.REGEXP_LOGIN_ID;

@Data
public class IdCheckDTO {
    @NotBlank
    @Pattern(regexp = REGEXP_LOGIN_ID)
    public String loginId;
}
