package www.kb.board.service;

import www.kb.board.dto.request.DeleteDTO;
import www.kb.board.dto.request.ListInfoDTO;
import www.kb.board.dto.request.ReplyRegisterDTO;
import www.kb.board.dto.request.WriteDTO;
import www.kb.board.dto.response.BoardDTO;
import www.kb.common.MyCriteria;
import www.kb.restful.dto.request.RestReplyRegisterDTO;
import www.kb.restful.dto.request.RestWriteDTO;

import java.util.List;
import java.util.Map;

public interface BoardService {
    MyCriteria getList(ListInfoDTO dto);

    Map<String, Object> registerArticle(WriteDTO dto);

    void increaseHits(int id);

    BoardDTO getArticleDetail(int id);

    BoardDTO getArticleDetailForUpdate(int id);

    Map<String, Object> updateArticle(WriteDTO dto);

    Map<String, Object> deleteArticle(DeleteDTO dto);

    List<BoardDTO> getListForRest();

    void registerArticleForRest(RestWriteDTO dto);

    BoardDTO getArticleDetailForRest(int id);

    Map<String, Object> registerReply(ReplyRegisterDTO dto);

    void registerReply(RestReplyRegisterDTO dto);
}
