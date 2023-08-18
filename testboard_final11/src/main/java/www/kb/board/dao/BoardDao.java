package www.kb.board.dao;

import www.kb.board.dto.request.DeleteDTO;
import www.kb.board.dto.request.ListInfoDTO;
import www.kb.board.dto.request.ReplyRegisterDTO;
import www.kb.board.dto.request.WriteDTO;
import www.kb.board.dto.response.BoardDTO;
import www.kb.common.MyCriteria;
import www.kb.restful.dto.request.RestReplyRegisterDTO;
import www.kb.restful.dto.request.RestWriteDTO;

import java.util.List;

public interface BoardDao {
    int getListCount(ListInfoDTO dto);

    List<BoardDTO> getList(MyCriteria mp);

    void registerArticle(WriteDTO dto);

    BoardDTO getArticleDetail(int id);

    void updateArticle(WriteDTO dto);

    void deleteArticle(DeleteDTO dto);

    void increaseHits(int id);

    List<BoardDTO> getListForRest();

    void registerArticleForRest(RestWriteDTO dto);

    void registerReply(ReplyRegisterDTO dto);

    void registerReply(RestReplyRegisterDTO dto);
}
