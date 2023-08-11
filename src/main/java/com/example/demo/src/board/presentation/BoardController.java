package com.example.demo.src.board.presentation;

import com.example.demo.src.board.application.BoardService;
import com.example.demo.src.board.dto.SaveBoardReq;
import com.example.demo.src.board.dto.UpdateBoardReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("opgg/board")
public class BoardController {

    private final BoardService boardService;

    //전체 게시글 조회 ,,, 페이징 관련 구현...
    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<?> findAllArticles() {
        return boardService.findAllArticles();
    }


    //글 등록
    //JWT 토큰과 함께 글 등록하는 기능으로 수정
    @PostMapping("/registration")
    @ResponseBody
    public ResponseEntity<?> save(@RequestBody SaveBoardReq saveBoardReq, HttpServletRequest request) {
        return boardService.save(saveBoardReq, request);
    }

    //내용만 수정
    @PatchMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody UpdateBoardReq updateBoardReq) {
        return boardService.update(id, updateBoardReq);
    }

    //게시글 삭제
    @DeleteMapping("{id}")
    @ResponseBody
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        return boardService.delete(id);
    }


}
