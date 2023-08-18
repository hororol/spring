package www.kb.board.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class ReplyRegisterDTO {
    private int aid;

    @NotBlank
    @Size(max = 100)
    private String contents;

    private String loginId;
}
