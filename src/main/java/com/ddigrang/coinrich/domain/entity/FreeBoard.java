package com.ddigrang.coinrich.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "FREE_BOARD_TB")
public class FreeBoard extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", length = 3000, nullable = false)
    private String content;

    @Column(length = 20, nullable = false)
    private String writer;

    @Column(nullable = false)
    private int view_cnt;

    @Column(nullable = false)
    private boolean notice_yn;

    @Column(nullable = false)
    private boolean delete_yn;

//    @Column(nullable = false)
//    @JsonFormat(shape = JsonFormat.Shape.STRING)
//    private Timestamp created_date;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING)
//    private Timestamp modified_date;

    @Column(length = 10, nullable = false)
    private String password;

    @Builder
    public FreeBoard(Long id,String title, String content, String writer, int view_cnt,
                     boolean notice_yn, boolean delete_yn, String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.view_cnt = view_cnt;
        this.notice_yn = notice_yn;
        this.delete_yn = delete_yn;
        this.password = password;
    }
}
