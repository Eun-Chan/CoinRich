package com.ddigrang.coinrich.freeboard.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="COMMENT_TB")
public class Comment extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String writer;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false, length = 10)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FREE_BOARD_TB_ID", nullable = false)
    private FreeBoard freeBoard;

    public void update(String content) {
        this.content = content;
    }

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "COMMENT_ID")
//    private Comment parent;

//    @OneToMany(mappedBy = "parent", orphanRemoval = true)
//    private List<Comment> children = new ArrayList<>();
}
