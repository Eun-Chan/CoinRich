package com.ddigrang.coinrich.freeboard.controller;

import com.ddigrang.coinrich.freeboard.dto.FreeBoardRequestDto;
import com.ddigrang.coinrich.freeboard.dto.FreeBoardResponseDto;
import com.ddigrang.coinrich.freeboard.service.FreeBoardService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FreeBoardApiController {

    private FreeBoardService freeBoardService;

    FreeBoardApiController(FreeBoardService freeBoardService) {
        this.freeBoardService = freeBoardService;
    }

    @GetMapping("/free-board")
    public List<FreeBoardResponseDto> findAll(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {
        return freeBoardService.findAll(pageable).getContent();
    }

    @PostMapping("/free-board")
    public Long save(@RequestBody FreeBoardRequestDto freeBoardRequestDto) {
        return freeBoardService.save(freeBoardRequestDto);
    }

//    @PostMapping(value = "/free-board", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
//    public Long save(@RequestPart("freeBoardRequestDto") FreeBoardRequestDto freeBoardRequestDto,
//                     @RequestPart("file") MultipartFile file,
//                    HttpServletRequest req) throws IOException {
//        String PATH = req.getSession().getServletContext().getRealPath("/");
//
//        if (!file.getOriginalFilename().isEmpty()) {
//            file.transferTo(new File(PATH + file.getOriginalFilename()));
//        }
//
//        return freeBoardService.saveWithImage(freeBoardRequestDto, file);
//    }

    @DeleteMapping("/free-board/{id}")
    public Long delete(@PathVariable final Long id) {
        return freeBoardService.delete(id);
    }

    @PatchMapping("/free-board/{id}") // /api/v1/free-board/{id}   /api/v1/free-board/5
    public Long update(@PathVariable final Long id ,@RequestBody FreeBoardRequestDto freeBoardRequestDto) {
        return freeBoardService.update(id, freeBoardRequestDto);
    }
}
