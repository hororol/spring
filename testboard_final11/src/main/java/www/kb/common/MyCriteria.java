package www.kb.common;

import lombok.Data;
import www.kb.board.dto.request.ListInfoDTO;
import www.kb.board.dto.response.BoardDTO;

import java.util.List;

@Data
public class MyCriteria extends ListInfoDTO {
    // 한 페이지에 보여줄 글의 개수
    private int articleCountPerPage = 10;

    // 페이지 그룹 요소 개수
    private int pageCountPerGroup = 5;

    // 글의 총 개수
    private int totalArticleCount;

    // 현재 페이지 번호
    private int nowPage;

    // 페이지 내 시작 글 번호
    private int startArticleNumber;

    // 총 페이지 개수
    private int totalPageCount;

    // 그룹 내 시작 페이지 번호
    private int startPage;

    // 그룹 내 끝 페이지 번호
    private int endPage;

    // 이전 페이지 번호
    private int prePage;

    // 다음 페이지 번호
    private int nextPage;

    private List<BoardDTO> list;

    public void setPaginationInfo(int nowPage, int totalArticleCount, ListInfoDTO dto) {
        this.nowPage = nowPage;
        this.totalArticleCount = totalArticleCount;

        // 페이지 개수 계산
        this.totalPageCount = (int) Math.ceil(
                (double) this.totalArticleCount / this.articleCountPerPage
        );

        // 페이지 번호 확정
        if (this.nowPage < 1) {
            this.nowPage = 1;
        }
        if (this.totalPageCount != 0 && this.nowPage > this.totalPageCount) {
            this.nowPage = this.totalPageCount;
        }

        // 이전 페이지 번호 계산
        this.prePage = this.nowPage - 1;
        if (prePage < 1) {
            this.prePage = 1;
        }

        // 다음 페이지 번호 계산
        this.nextPage = this.nowPage + 1;
        if (this.nextPage > this.totalPageCount) {
            this.nextPage = this.totalPageCount;
        }

        this.startArticleNumber = (this.nowPage - 1) * this.articleCountPerPage;

        this.startPage =
                ((int) (((double) this.nowPage / this.pageCountPerGroup + 0.9) - 1))
                        * this.pageCountPerGroup + 1;
        this.endPage = this.startPage + (this.pageCountPerGroup - 1);
        if (this.endPage > this.totalPageCount) {
            this.endPage = this.totalPageCount;
        }

        setListInfoDTO(dto);
    }

    public void setListInfoDTO(ListInfoDTO dto) {
        super.setPage(this.nowPage);
        super.setCategory(dto.getCategory());
        super.setKeyword(dto.getKeyword());
        super.setSort(dto.getSort());
        super.setAid(dto.getAid());
    }
}









