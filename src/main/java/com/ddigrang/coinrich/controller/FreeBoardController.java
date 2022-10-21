package com.ddigrang.coinrich.controller;

import com.ddigrang.coinrich.dto.FreeBoardDto;
import com.ddigrang.coinrich.service.FreeBoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;

@Controller
@RequestMapping("/free-board")
public class FreeBoardController {

    private FreeBoardService freeBoardService;

    public FreeBoardController(FreeBoardService freeBoardService) {
        this.freeBoardService = freeBoardService;
    }

    @PostMapping("/write")
    public String register(FreeBoardDto freeBoardDto) {
        freeBoardService.register(freeBoardDto);
        return "redirect:/free-board/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, @PageableDefault(sort="id", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {
        Page<FreeBoardDto> freeBoardDtoList = freeBoardService.getBoardList(pageable);

        // 현재 페이지
        int pageNumber = freeBoardDtoList.getPageable().getPageNumber();
        // 총 페이지
        int totalPages = freeBoardDtoList.getTotalPages();
        int pageBlock = 5;
        int startBlockPage  = ((pageNumber)/pageBlock)*pageBlock + 1;
        int endBlockPage = startBlockPage+pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("freeBoardList", freeBoardDtoList.getContent());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("startBlockPage",startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);

        model.addAttribute("freeBoard",freeBoardService.getBoard(id));
        return "board/freeBoardDetail";
    }

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(sort="id", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {
        Page<FreeBoardDto> freeBoardDtoList = freeBoardService.getBoardList(pageable);

        // 현재 페이지
        int pageNumber = freeBoardDtoList.getPageable().getPageNumber();
        // 총 페이지
        int totalPages = freeBoardDtoList.getTotalPages();
        int pageBlock = 5;
        int startBlockPage  = ((pageNumber)/pageBlock)*pageBlock + 1;
        int endBlockPage = startBlockPage+pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("freeBoardList", freeBoardDtoList.getContent());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("startBlockPage",startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);

        return "board/freeBoardList";
    }

    @GetMapping("/form")
    public String form(Model model, @PageableDefault(sort="id", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {
        Page<FreeBoardDto> freeBoardDtoList = freeBoardService.getBoardList(pageable);

        // 현재 페이지
        int pageNumber = freeBoardDtoList.getPageable().getPageNumber();
        // 총 페이지
        int totalPages = freeBoardDtoList.getTotalPages();
        int pageBlock = 5;
        int startBlockPage  = ((pageNumber)/pageBlock)*pageBlock + 1;
        int endBlockPage = startBlockPage+pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("freeBoardList", freeBoardDtoList.getContent());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("startBlockPage",startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);

        return "board/freeBoardWrite";
    }
}
