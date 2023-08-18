package www.kb.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import www.kb.board.dao.BoardDao;
import www.kb.board.dto.request.DeleteDTO;
import www.kb.board.dto.request.ListInfoDTO;
import www.kb.board.dto.request.ReplyRegisterDTO;
import www.kb.board.dto.request.WriteDTO;
import www.kb.board.dto.response.BoardDTO;
import www.kb.common.CookieChecker;
import www.kb.common.MyCriteria;
import www.kb.common.Validator;
import www.kb.restful.dto.request.RestReplyRegisterDTO;
import www.kb.restful.dto.request.RestWriteDTO;
import www.kb.security.util.LoginUtil;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardDao dao;

    private void initListInfoDTO(ListInfoDTO dto) {
        if (StringUtils.isEmpty(dto.getCategory())) {
            dto.setCategory("title");
        }

        if (dto.getKeyword() == null) {
            dto.setKeyword("");
        }

        if (StringUtils.isEmpty(dto.getSort())) {
            dto.setSort("recent");
        }
    }

    @Override
    public MyCriteria getList(ListInfoDTO dto) {
        initListInfoDTO(dto);

        int totalArticleCount = dao.getListCount(dto);

        MyCriteria mp = new MyCriteria();
        mp.setPaginationInfo(dto.getPage()
                , totalArticleCount
                , dto);

        mp.setList(dao.getList(mp));

        return mp;
    }

    @Override
    @Transactional
    public Map<String, Object> registerArticle(WriteDTO dto) {
        dto.setTitle(Validator.changeToString(dto.getTitle()));
        dto.setContents(Validator.changeToString(dto.getContents()));
        dto.setLoginId(LoginUtil.getLoginId());

        dao.registerArticle(dto);

        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", true);
        map.put("message", "글을 등록하였습니다.");
        return map;
    }

    @Override
    public BoardDTO getArticleDetail(int id) {
        BoardDTO dto = dao.getArticleDetail(id);
        if (dto == null) {
            throw new RuntimeException("삭제된 글입니다.");
        }

        if (dto.getRegisterLoginId().equals(LoginUtil.getLoginId())) {
            dto.setRegister(true);
        }

        dto.setContents(Validator.changeToHtml(dto.getContents()));

        return dto;
    }

    @Override
    public BoardDTO getArticleDetailForUpdate(int id) {
        BoardDTO dto = dao.getArticleDetail(id);
        if (dto == null) {
            throw new RuntimeException("삭제된 글입니다.");
        }

        if (!dto.getRegisterLoginId().equals(LoginUtil.getLoginId())) {
            throw new RuntimeException("잘못된 접근입니다.");
        }

        return dto;
    }

    @Override
    @Transactional
    public Map<String, Object> updateArticle(WriteDTO dto) {
        dto.setTitle(Validator.changeToString(dto.getTitle()));
        dto.setContents(Validator.changeToString(dto.getContents()));
        dto.setLoginId(LoginUtil.getLoginId());

        dao.updateArticle(dto);

        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", true);
        map.put("message", "글을 수정하였습니다.");
        return map;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteArticle(DeleteDTO dto) {
        dto.setLoginId(LoginUtil.getLoginId());

        dao.deleteArticle(dto);

        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", true);
        map.put("message", "글을 삭제하였습니다.");
        return map;
    }

    @Override
    @Transactional
    public void increaseHits(int id) {
        dao.increaseHits(id);
    }

    @Override
    public List<BoardDTO> getListForRest() {
        List<BoardDTO> list = dao.getListForRest();
        return list;
    }

    @Override
    @Transactional
    public void registerArticleForRest(RestWriteDTO dto) {
        dto.setTitle(Validator.changeToString(dto.getTitle()));
        dto.setContents(Validator.changeToString(dto.getContents()));

        dao.registerArticleForRest(dto);
    }

    @Override
    public BoardDTO getArticleDetailForRest(int id) {
        BoardDTO dto = dao.getArticleDetail(id);
        if (dto == null) {
            throw new RuntimeException("삭제된 글입니다.");
        }

        dto.setContents(Validator.changeToHtml(dto.getContents()));

        return dto;
    }

    @Override
    @Transactional
    public Map<String, Object> registerReply(ReplyRegisterDTO dto) {
        dto.setContents(Validator.changeToString(dto.getContents()));
        dto.setLoginId(LoginUtil.getLoginId());

        dao.registerReply(dto);

        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", true);
        map.put("message", "댓글을 등록하였습니다.");
        return map;
    }

    @Override
    @Transactional
    public void registerReply(RestReplyRegisterDTO dto) {
        dto.setContents(Validator.changeToString(dto.getContents()));

        dao.registerReply(dto);
    }
}
