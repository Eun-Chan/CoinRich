package com.ddigrang.coinrich.freeboard.service;

import com.ddigrang.coinrich.freeboard.domain.entity.FreeBoard;
import com.ddigrang.coinrich.freeboard.domain.repository.CommentRepository;
import com.ddigrang.coinrich.freeboard.domain.repository.FreeBoardRepository;
import com.ddigrang.coinrich.freeboard.dto.CommentRequestDto;
import com.ddigrang.coinrich.freeboard.dto.FreeBoardDto;
import com.ddigrang.coinrich.freeboard.dto.FreeBoardRequestDto;
import com.ddigrang.coinrich.freeboard.dto.FreeBoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {

    private FreeBoardRepository freeBoardRepository;

    FreeBoardServiceImpl(FreeBoardRepository freeBoardRepository) {
        this.freeBoardRepository = freeBoardRepository;

    }

    @Transactional
    public Long save(FreeBoardRequestDto freeBoardRequestDto) {
        return freeBoardRepository.save(freeBoardRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long saveWithImage(FreeBoardRequestDto freeBoardRequestDto, MultipartFile file) {
//        freeBoardRequestDto.setFile_name(file.getOriginalFilename());
//        freeBoardRequestDto.setFile_path(file.getName());

        return freeBoardRepository.save(freeBoardRequestDto.toEntity()).getId();
    }

    @Transactional
    public Page<FreeBoardResponseDto> findAll(Pageable pageable) {
        Page<FreeBoard> freeBoards = freeBoardRepository.findAll(pageable);

        return freeBoards.map(entity -> new FreeBoardResponseDto(entity));
    }

    @Transactional
    public FreeBoardResponseDto findById(Long id) {
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. (게시글 ID : "+id+")"));

        return new FreeBoardResponseDto(freeBoard);
    }

    @Transactional
    public Long delete(Long id) {
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. (게시글 ID : "+id+")"));
        freeBoardRepository.delete(freeBoard);

        return id;
    }

    /* UPDATE (dirty checking 영속성 컨텍스트)
     *  FreeBoard 객체를 영속화시키고, 영속화된 FreeBoard 객체를 가져와 데이터를 변경하면
     * 트랜잭션이 끝날 때 자동으로 DB에 저장해준다. */
    @Transactional
    public Long update(Long id, FreeBoardRequestDto freeBoardRequestDto) {
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. (게시글 ID : "+id+")"));

        freeBoard.update(freeBoardRequestDto.getWriter(), freeBoardRequestDto.getTitle(), freeBoardRequestDto.getContent(), freeBoardRequestDto.getPassword());

        return id;
    }
}
