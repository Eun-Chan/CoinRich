package com.ddigrang.coinrich.dto;

import com.ddigrang.coinrich.domain.entity.FreeBoard;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
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

    @Builder
    public FreeBoardDto(Long id, String title, String content, String writer,
                        int view_cnt, boolean notice_yn, boolean delete_yn,
                        LocalDateTime created_date, LocalDateTime modified_date,String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.view_cnt = view_cnt;
        this.notice_yn = notice_yn;
        this.delete_yn = delete_yn;
        this.created_date = created_date;
        this.modified_date = modified_date;
        this.password = password;
    }
}
