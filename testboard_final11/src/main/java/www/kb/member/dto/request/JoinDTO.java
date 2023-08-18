package www.kb.member.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import www.kb.common.SelectionData;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

import static www.kb.common.RegexpConstants.*;

@Data
public class JoinDTO {
    @NotBlank
    @Pattern(regexp = REGEXP_LOGIN_ID)
    private String loginId;

    @NotBlank
    @Pattern(regexp = REGEXP_PASSWORD)
    private String password;

    @NotBlank
    private String passwordCheck;

    @NotBlank
    @Pattern(regexp = REGEXP_NAME)
    private String name;

    @NotNull
    @SelectionData(regexp = REGEXP_BIRTHDAY)
    private String strBirthday;

    @NotBlank
    @Pattern(regexp = REGEXP_MOBILE_NO)
    private String mobileNo;

    @NotBlank
    @Pattern(regexp = REGEXP_EMAIL)
    private String email;

    @NotNull
    @SelectionData(regexp = REGEXP_ZIPCODE)
    private String zipcode;

    @NotNull
    @SelectionData(regexp = REGEXP_ADDRESS)
    private String address;

    @NotNull
    @SelectionData(regexp = REGEXP_ADDRESS_DETAIL)
    private String addressDetail;

    private LocalDate birthday;
    private int memberDetailId;

}
