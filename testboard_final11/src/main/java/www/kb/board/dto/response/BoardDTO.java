package www.kb.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private int id;
    private String title;
    private String contents;
    private int hits;
    private int registerId;
    private LocalDateTime registerDatetime;
    private String registerLoginId;
    private boolean register;
    private String strRegisterDatetime;

    private List<ReplyDTO> replyList;
    private int replyCount;
}
