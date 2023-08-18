package www.kb.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {
    private int id;
    private String contents;
    private int registerId;
    private int articleId;
    private LocalDateTime registerDatetime;

    private String registerLoginId;
}
