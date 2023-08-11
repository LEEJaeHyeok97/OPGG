package com.example.demo.src.board.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/opgg")
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    public String index() {
        return null;
    }
}
