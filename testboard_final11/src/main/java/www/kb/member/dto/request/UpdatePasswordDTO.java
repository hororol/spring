package www.kb.member.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

import static www.kb.common.RegexpConstants.REGEXP_PASSWORD;

@Data
public class UpdatePasswordDTO {
    @NotBlank
    @Pattern(regexp = REGEXP_PASSWORD)
    private String password;

    @NotBlank
    private String passwordCheck;

    private String loginId;
}
