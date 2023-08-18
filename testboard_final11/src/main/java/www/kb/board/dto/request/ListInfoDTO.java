package www.kb.board.dto.request;

import lombok.Data;

@Data
public class ListInfoDTO {
    private int page;
    private String category;
    private String keyword;
    private String sort;
    private int aid;
}
