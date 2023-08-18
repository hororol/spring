package www.kb.board.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@Data
public class WriteDTO {
    @NotBlank
    @Size (max = 50)
    private String title;

    @NotBlank
    private String contents;
    
    private int aid;

    private String loginId;
}
