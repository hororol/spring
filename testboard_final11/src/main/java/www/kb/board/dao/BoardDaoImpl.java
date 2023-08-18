package www.kb.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import www.kb.board.dto.request.DeleteDTO;
import www.kb.board.dto.request.ListInfoDTO;
import www.kb.board.dto.request.ReplyRegisterDTO;
import www.kb.board.dto.request.WriteDTO;
import www.kb.board.dto.response.BoardDTO;
import www.kb.common.MyCriteria;
import www.kb.restful.dto.request.RestReplyRegisterDTO;
import www.kb.restful.dto.request.RestWriteDTO;

import java.util.List;

@Repository
public class BoardDaoImpl implements BoardDao {
    @Autowired
    private SqlSession session;

    @Override
    public int getListCount(ListInfoDTO dto) {
        return session.selectOne("board.selectBoardListCount", dto);
    }

    @Override
    public List<BoardDTO> getList(MyCriteria mp) {
        return session.selectList("board.selectBoardList", mp);
    }

    @Override
    public void registerArticle(WriteDTO dto) {
        session.insert("board.insertBoard", dto);
    }

    @Override
    public BoardDTO getArticleDetail(int id) {
        return session.selectOne("board.selectBoardDetailById", id);
    }

    @Override
    public void updateArticle(WriteDTO dto) {
        session.update("board.updateBoard", dto);
    }

    @Override
    public void deleteArticle(DeleteDTO dto) {
        session.update("board.updateDeleteState", dto);
    }

    @Override
    public void increaseHits(int id) {
        session.update("board.updateBoardHits", id);
    }

    @Override
    public List<BoardDTO> getListForRest() {
        return session.selectList("board.selectListForRest");
    }

    @Override
    public void registerArticleForRest(RestWriteDTO dto) {
        session.insert("board.insertArticleForRest", dto);
    }

    @Override
    public void registerReply(ReplyRegisterDTO dto) {
        session.insert("board.insertReply", dto);
    }

    @Override
    public void registerReply(RestReplyRegisterDTO dto) {
        session.insert("board.insertReply", dto);
    }
}









