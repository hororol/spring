package www.kb.restful.control;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import www.kb.board.dto.request.ListInfoDTO;
import www.kb.board.dto.response.BoardDTO;
import www.kb.board.service.BoardService;
import www.kb.restful.dto.request.RestReplyRegisterDTO;
import www.kb.restful.dto.request.RestWriteDTO;
import www.kb.restful.dto.response.ResponseDTO;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/rest")
@CrossOrigin
public class TestRestfulController {
    @Autowired
    private BoardService service;

    @GetMapping
    public ResponseEntity getList() {
        try {
            List<BoardDTO> list = service.getListForRest();
            ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder()
                    .list(list)
                    .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ResponseDTO response = ResponseDTO.builder()
                    .message("글 목록 로딩에 실패하였습니다.")
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping
    public ResponseEntity registerArticle(@RequestBody RestWriteDTO dto) {
        try {
            service.registerArticleForRest(dto);
            ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder()
                    .message("글을 저장하였습니다.")
                    .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ResponseDTO response = ResponseDTO.builder()
                    .message("글 저장에 실패하였습니다.")
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{aid}")
    public ResponseEntity getArticleDetail(@PathVariable int aid) {
        try {
            return ResponseEntity.ok().body(service.getArticleDetailForRest(aid));
        } catch (Exception e) {
            ResponseDTO response = ResponseDTO.builder()
                    .message("글 불러오기에 실패하였습니다.")
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/reply")
    public ResponseEntity registerReply(@RequestBody RestReplyRegisterDTO dto) {
        try {
            service.registerReply(dto);
            ResponseDTO response = ResponseDTO.builder()
                    .message("댓글을 등록하였습니다.")
                    .build();

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ResponseDTO response = ResponseDTO.builder()
                    .message("댓글 저장에 실패하였습니다.")
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}





