package com.ddigrang.coinrich.freeboard.dto;

import com.ddigrang.coinrich.freeboard.domain.entity.FreeBoard;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class FreeBoardDto {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private int view_cnt;
    private boolean notice_yn;
    private boolean delete_yn;
    private LocalDateTime created_date;
    private LocalDateTime modified_date;
    private String password;

    public FreeBoard toEntity() {
        return FreeBoard.builder()
                .id(id)
                .title(title)
                .content(content)
                .writer(writer)
                .view_cnt(view_cnt)
                .notice_yn(notice_yn)
                .delete_yn(delete_yn)
                .password(password)
                .build();
    }
}
