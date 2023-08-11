package com.example.demo.src.board.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
public class UpdateBoardReq {

    private Long id;


    private String boardContents;
}
