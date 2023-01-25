package com.ddigrang.coinrich.freeboard.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "FREE_BOARD_TB")
public class FreeBoard extends TimeEntity {

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
    private Integer view_cnt;

    @Column(nullable = false)
    private boolean notice_yn;

    @Column(nullable = false)
    private boolean delete_yn;

    @Column(length = 10, nullable = false)
    private String password;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "freeBoard",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    @OrderBy("id asc")
    private List<Comment> comments = new ArrayList<>();

//    private String file_name;
//
//    private String file_path;

    public void update(String writer,String title, String content, String password) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.password = password;
    }
}
