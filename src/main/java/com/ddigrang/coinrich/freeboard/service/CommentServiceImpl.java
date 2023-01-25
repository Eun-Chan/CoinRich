package com.ddigrang.coinrich.freeboard.service;

import com.ddigrang.coinrich.freeboard.domain.entity.Comment;
import com.ddigrang.coinrich.freeboard.domain.entity.FreeBoard;
import com.ddigrang.coinrich.freeboard.domain.repository.CommentRepository;
import com.ddigrang.coinrich.freeboard.domain.repository.FreeBoardRepository;
import com.ddigrang.coinrich.freeboard.dto.CommentRequestDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private FreeBoardRepository freeBoardRepository;

    CommentServiceImpl(CommentRepository commentRepository, FreeBoardRepository freeBoardRepository) {
        this.commentRepository = commentRepository;
        this.freeBoardRepository = freeBoardRepository;
    }

    @Transactional
    public Long save(Long id, CommentRequestDto commentRequestDto) {
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. (게시글 ID : "+id+")"));

        commentRequestDto.setFreeBoard(freeBoard);

        Comment comment = commentRequestDto.toEntity();

        return commentRepository.save(comment).getId();
    }

    @Transactional
    public Long delete(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글은 존재하지 않습니다. (댓글 ID : )"+id+")"));

        commentRepository.delete(comment);

        return id;
    }

    @Transactional
    public Long update(Long id, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글은 존재하지 않습니다. (댓글 ID : )"+id+")"));

        comment.update(commentRequestDto.getContent());

        return id;
    }

}
