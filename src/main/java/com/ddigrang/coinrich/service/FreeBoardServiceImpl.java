package com.ddigrang.coinrich.service;

import com.ddigrang.coinrich.domain.entity.FreeBoard;
import com.ddigrang.coinrich.domain.repository.FreeBoardRepository;
import com.ddigrang.coinrich.dto.FreeBoardDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {

    private FreeBoardRepository freeBoardRepository;

    private ModelMapper modelMapper;

    public FreeBoardServiceImpl(FreeBoardRepository freeBoardRepository,ModelMapper modelMapper) {
        this.freeBoardRepository = freeBoardRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Long register(FreeBoardDto freeBoardDto) {
        return freeBoardRepository.save(freeBoardDto.toEntity()).getId();
    }

    @Transactional
    public Page<FreeBoardDto> getBoardList(Pageable pageable) {
        Page<FreeBoard> freeBoards = freeBoardRepository.findAll(pageable);

        Page<FreeBoardDto> freeBoardDtos = freeBoards.map(freeBoard -> FreeBoardDto.builder()
                .id(freeBoard.getId())
                .title(freeBoard.getTitle())
                .content(freeBoard.getContent())
                .writer(freeBoard.getWriter())
                .view_cnt(freeBoard.getView_cnt())
                .notice_yn(freeBoard.isNotice_yn())
                .delete_yn(freeBoard.isDelete_yn())
                .created_date(freeBoard.getCreated_date())
                .modified_date(freeBoard.getModified_date())
                .password(freeBoard.getPassword())
                .build());

        return freeBoardDtos;
    }

    @Transactional
    public FreeBoardDto getBoard(Long id) {
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElse(null);

        return modelMapper.map(freeBoard, FreeBoardDto.class);
    }
}
