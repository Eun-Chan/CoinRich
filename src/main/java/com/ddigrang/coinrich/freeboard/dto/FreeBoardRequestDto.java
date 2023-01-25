package com.ddigrang.coinrich.freeboard.dto;

import com.ddigrang.coinrich.freeboard.domain.entity.FreeBoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreeBoardRequestDto {

    private String title;
    private String content;
    private String writer;
    private String password;
    private Integer view_cnt;
    private boolean notice_yn;
    private boolean delete_yn;
//    private String file_name;
//    private String file_path;

    /**
     * Dto to Entity (FreeBoard)
     * @return
     */
    public FreeBoard toEntity() {
        return FreeBoard.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .password(password)
                .view_cnt(0)
                .notice_yn(false)
                .delete_yn(false)
//                .file_name(file_name)
//                .file_path(file_path)
                .build();
    }
}
