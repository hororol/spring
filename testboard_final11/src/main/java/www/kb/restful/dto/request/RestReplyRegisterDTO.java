package www.kb.restful.dto.request;

import lombok.Data;

@Data
public class RestReplyRegisterDTO {
    private int aid;

    private String contents;

    private String loginId;
}
