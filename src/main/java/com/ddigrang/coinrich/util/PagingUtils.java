package com.ddigrang.coinrich.util;

import com.ddigrang.coinrich.freeboard.dto.FreeBoardDto;
import com.ddigrang.coinrich.freeboard.dto.FreeBoardResponseDto;
import com.ddigrang.coinrich.freeboard.service.FreeBoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

public class PagingUtils {


    private static FreeBoardService freeBoardService;

    public static Model freeBoardPage(Model model, Pageable pageable) {
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

        return model;
    }
}
