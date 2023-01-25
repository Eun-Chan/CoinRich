package com.ddigrang.coinrich.freeboard.service;

import com.ddigrang.coinrich.freeboard.dto.CommentRequestDto;
import com.ddigrang.coinrich.freeboard.dto.CommentResponseDto;

import java.util.List;

public interface CommentService {

    Long save(Long id, CommentRequestDto commentRequestDto);

    Long delete(Long id);

    Long update(Long id, CommentRequestDto commentRequestDto);
}
