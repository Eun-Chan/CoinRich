package com.ddigrang.coinrich.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

//    @RequestMapping("/board/freeBoardList")
//    public String freeBoardList() {
//        return "board/freeBoardList";
//    }
}
