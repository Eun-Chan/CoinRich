package com.ddigrang.coinrich.freeboard.dto;

import com.ddigrang.coinrich.freeboard.domain.entity.FreeBoard;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class FreeBoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private Integer view_cnt;
    private boolean notice_yn;
    private boolean delete_yn;
    private LocalDateTime created_date;
    private LocalDateTime modified_date;
    private String password;
    private List<CommentResponseDto> comments;

    /**
     * Entity to Dto (freeBoard)
     */
    public FreeBoardResponseDto(FreeBoard freeBoard) {
        this.id = freeBoard.getId();
        this.title = freeBoard.getTitle();
        this.content = freeBoard.getContent();
        this.writer = freeBoard.getWriter();
        this.view_cnt = freeBoard.getView_cnt();
        this.notice_yn = freeBoard.isNotice_yn();
        this.delete_yn = freeBoard.isDelete_yn();
        this.created_date = freeBoard.getCreated_date();
        this.modified_date = freeBoard.getModified_date();
        this.password = freeBoard.getPassword();
        this.comments = freeBoard.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
}
