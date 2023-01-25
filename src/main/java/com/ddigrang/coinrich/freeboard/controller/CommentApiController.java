package com.ddigrang.coinrich.freeboard.controller;

import com.ddigrang.coinrich.freeboard.dto.CommentRequestDto;
import com.ddigrang.coinrich.freeboard.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CommentApiController {

    private CommentService commentService;

    CommentApiController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/free-board/{id}/comment")
    public Long save(@PathVariable final Long id, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.save(id, commentRequestDto);
    }
}
