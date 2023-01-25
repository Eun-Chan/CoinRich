package com.ddigrang.coinrich.freeboard.dto;

import com.ddigrang.coinrich.freeboard.domain.entity.Comment;
import com.ddigrang.coinrich.freeboard.domain.entity.FreeBoard;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private Long id;
    private String writer;
    private String content;
    private FreeBoard freeBoard;
    private String password;
    private LocalDateTime created_date;
    private LocalDateTime modified_date;
//    private Comment parent;
//    private List<Comment> children;

    /* Entity to Dto */
    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.writer = comment.getWriter();
        this.content = comment.getContent();
        this.freeBoard = comment.getFreeBoard();
        this.password = comment.getPassword();
        this.created_date = comment.getCreated_date();
        this.modified_date = comment.getModified_date();
//        this.parent = comment.getParent();
//        this.children = comment.getChildren();
    }
}
