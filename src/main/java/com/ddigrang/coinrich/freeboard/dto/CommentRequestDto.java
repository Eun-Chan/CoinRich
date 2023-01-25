package com.ddigrang.coinrich.freeboard.dto;

import com.ddigrang.coinrich.freeboard.domain.entity.Comment;
import com.ddigrang.coinrich.freeboard.domain.entity.FreeBoard;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentRequestDto {

    private Long id;
    private String writer;
    private String content;
    private String password;
    private FreeBoard freeBoard;
//    private Comment parent;
//    private List<Comment> children = new ArrayList<>();

    public Comment toEntity() {
        return Comment.builder()
                .id(id)
                .writer(writer)
                .content(content)
                .password(password)
                .freeBoard(freeBoard)
//                .parent(parent)
//                .children(children)
                .build();
    }
}
