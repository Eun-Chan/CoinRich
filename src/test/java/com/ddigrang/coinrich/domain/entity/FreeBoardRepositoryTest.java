package com.ddigrang.coinrich.domain.entity;

import com.ddigrang.coinrich.freeboard.domain.repository.FreeBoardRepository;
import com.ddigrang.coinrich.freeboard.domain.entity.FreeBoard;
import com.ddigrang.coinrich.freeboard.dto.FreeBoardResponseDto;
import com.ddigrang.coinrich.freeboard.service.FreeBoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

@SpringBootTest
class FreeBoardRepositoryTest {

    @Autowired
    FreeBoardRepository freeBoardRepository;

    @Autowired
    FreeBoardService freeBoardService;

    @Test
    void 게시물_등록() {
        String title = "테스트 타이틀";
        String content = "테스트 컨텐츠";
        String writer = "테스트 ";
        int view_cnt = 2;
        boolean notice_yn = false;
        boolean delete_yn = false;
        String password = "1234";

        for(int i = 1; i < 150; i++) {
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
    void findAll_테스트() {
        Pageable pageable = PageRequest.of(0,20);

        Page<FreeBoardResponseDto> freeBoardList = freeBoardService.findAll(pageable);

        for(FreeBoardResponseDto freeBoardResponseDto: freeBoardList)
            System.out.println(freeBoardResponseDto.toString());
    }

    @Test
    void findById_테스트() {
        FreeBoardResponseDto freeBoardResponseDto = freeBoardService.findById(1L);

        System.out.println(freeBoardResponseDto.toString());
    }
}