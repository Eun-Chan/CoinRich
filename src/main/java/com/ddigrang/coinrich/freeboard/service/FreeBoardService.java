package com.ddigrang.coinrich.freeboard.service;

import com.ddigrang.coinrich.freeboard.dto.FreeBoardRequestDto;
import com.ddigrang.coinrich.freeboard.dto.FreeBoardResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FreeBoardService {

    Long save(FreeBoardRequestDto freeBoardRequestDto);

    Long saveWithImage(FreeBoardRequestDto freeBoardRequestDto, MultipartFile file);

    Page<FreeBoardResponseDto> findAll(Pageable pageable);

    FreeBoardResponseDto findById(Long id);

    Long delete(Long id);

    Long update(Long id, FreeBoardRequestDto freeBoardRequestDto);
}