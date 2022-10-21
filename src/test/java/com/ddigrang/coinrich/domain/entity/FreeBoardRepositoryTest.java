package com.ddigrang.coinrich.domain.entity;

import com.ddigrang.coinrich.domain.repository.FreeBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;

@SpringBootTest
class FreeBoardRepositoryTest {

    @Autowired
    FreeBoardRepository freeBoardRepository;

    @Test
    void 게시물_등록() {
        String title = "테스트 타이틀";
        String content = "테스트 컨텐츠";
        String writer = "테스트 ";
        int view_cnt = 2;
        boolean notice_yn = false;
        boolean delete_yn = false;
        String password = "1234";

        for(int i = 201; i < 500; i++) {
            freeBoardRepository.save(FreeBoard.builder()
                    .title(title+i)
                    .content(content+i)
                    .writer(writer+i)
                    .view_cnt(i)
                    .notice_yn(notice_yn)
                    .delete_yn(delete_yn)
                    .password(password)
                    .build());
        }
    }

    @Test
    void 게시물_전체조회() {
        List<FreeBoard> freeBoardList = freeBoardRepository.findAll();

        for(int i = 0; i < freeBoardList.size(); i++) {
            System.out.println(freeBoardList.get(i).toString()+" "+freeBoardList.get(i).getCreated_date()+ " "+freeBoardList.get(i).getModified_date());

        }
    }
}