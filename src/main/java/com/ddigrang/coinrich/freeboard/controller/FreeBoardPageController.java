package com.ddigrang.coinrich.freeboard.controller;

import com.ddigrang.coinrich.freeboard.dto.FreeBoardRequestDto;
import com.ddigrang.coinrich.freeboard.dto.FreeBoardResponseDto;
import com.ddigrang.coinrich.freeboard.service.FreeBoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/free-board")
public class FreeBoardPageController {

    private FreeBoardService freeBoardService;

    FreeBoardPageController(FreeBoardService freeBoardService) {
        this.freeBoardService = freeBoardService;
    }

    @PostMapping("/write")
    public String save(FreeBoardRequestDto freeBoardRequestDto) {
        freeBoardService.save(freeBoardRequestDto);
        return "redirect:/free-board/list";
    }


    @GetMapping("/detail/{id}")
    public String read(@PathVariable("id") Long id, Model model, @PageableDefault(sort="id", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {
        Page<FreeBoardResponseDto> freeBoardDtoList = freeBoardService.findAll(pageable);

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

        model.addAttribute("freeBoard",freeBoardService.findById(id));
        return "board/freeBoardDetail";
    }

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(sort="id", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {
        Page<FreeBoardResponseDto> freeBoardDtoList = freeBoardService.findAll(pageable);

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
        Page<FreeBoardResponseDto> freeBoardDtoList = freeBoardService.findAll(pageable);

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

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        FreeBoardResponseDto freeBoardResponseDto = freeBoardService.findById(id);

        model.addAttribute("freeBoard", freeBoardResponseDto);
        return "board/freeBoardUpdate";
    }
}
