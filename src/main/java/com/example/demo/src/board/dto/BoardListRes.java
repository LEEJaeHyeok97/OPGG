package com.example.demo.src.board.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class BoardListRes {
    private Long id;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;


    @Builder
    public BoardListRes(Long id, String boardWriter, String boardTitle, String boardContents) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
    }
}
