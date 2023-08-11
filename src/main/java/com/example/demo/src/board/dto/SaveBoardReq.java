package com.example.demo.src.board.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
public class SaveBoardReq {

    @NotNull
    private String boardWriter;

    @NotNull
    private String boardTitle;

    @NotNull
    private String boardContents;
}
