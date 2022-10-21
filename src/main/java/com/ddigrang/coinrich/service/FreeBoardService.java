package com.ddigrang.coinrich.service;

import com.ddigrang.coinrich.dto.FreeBoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FreeBoardService {

    Long register(FreeBoardDto freeBoardDto);

    Page<FreeBoardDto> getBoardList(Pageable pageable);

    FreeBoardDto getBoard(Long id);
}