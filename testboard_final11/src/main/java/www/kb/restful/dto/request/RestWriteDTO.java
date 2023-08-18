package www.kb.restful.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@Data
public class RestWriteDTO {
    @NotBlank
    @Size(max = 50)
    private String title;

    @NotBlank
    private String contents;

    @NotBlank
    private String loginId;
}
