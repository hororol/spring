package www.kb.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import www.kb.board.dto.request.ListInfoDTO;
import www.kb.board.dto.response.BoardDTO;
import www.kb.board.service.BoardService;
import www.kb.common.CookieChecker;
import www.kb.common.MyCriteria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/board")
public class BoardController {
    @Autowired
    private BoardService service;

    @ExceptionHandler(Exception.class)
    public String procException() {
        return "redirect:/error";
    }

    @ExceptionHandler(RuntimeException.class)
    public String procRuntimeException(HttpServletRequest request, Exception e) {
        request.setAttribute("message", e.getMessage());
        return "alert";
    }

    // 게시판 목록 이동
    @RequestMapping(value = "/list")
    public String gotoList(Model model, ListInfoDTO info) {
        MyCriteria mc = service.getList(info);
        model.addAttribute("mc", mc);
        return "board/list";
    }

    // 게시판 글쓰기 폼 이동
    @RequestMapping(value = "/write")
    public String gotoWriteForm(Model model, @ModelAttribute ListInfoDTO info) {
        return "board/writeForm";
    }

    // 게시판 글 상세 보기 페이지 이동
    @RequestMapping(value = "/detail")
    public String gotoBoardDetail(Model model
            , HttpServletRequest request
            , HttpServletResponse response
            , @ModelAttribute ListInfoDTO info) {
        CookieChecker checker = new CookieChecker();
        if (checker.cookieCheck(request, response, info.getAid(), "board")) {
            try {
                service.increaseHits(info.getAid());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }
        BoardDTO dto = service.getArticleDetail(info.getAid());
        model.addAttribute("dto", dto);

        return "board/detail";
    }

    // 게시판 글 업데이트 페이지 이동
    @RequestMapping(value = "/update")
    public String gotoBoardUpdateForm(Model model, @ModelAttribute ListInfoDTO info) {
        BoardDTO dto = service.getArticleDetailForUpdate(info.getAid());
        model.addAttribute("dto", dto);

        return "board/updateForm";
    }
}







