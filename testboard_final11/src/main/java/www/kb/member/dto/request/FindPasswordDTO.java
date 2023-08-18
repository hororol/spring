package www.kb.member.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

import static www.kb.common.RegexpConstants.*;

@Data
public class FindPasswordDTO {
    @NotBlank
    private String loginId;

    @NotBlank
    @Pattern(regexp = REGEXP_NAME)
    private String name;

    @NotBlank
    @Pattern(regexp = REGEXP_MOBILE_NO)
    private String mobileNo;

    @NotBlank
    @Pattern(regexp = REGEXP_EMAIL)
    private String email;

}
