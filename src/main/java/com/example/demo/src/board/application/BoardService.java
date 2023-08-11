package com.example.demo.src.board.application;

import com.example.demo.global.payload.Message;
import com.example.demo.src.board.domain.Board;
import com.example.demo.src.board.domain.repository.BoardRepository;
import com.example.demo.src.board.dto.BoardListRes;
import com.example.demo.src.board.dto.SaveBoardReq;
import com.example.demo.global.payload.ApiResponse;
import com.example.demo.src.board.dto.UpdateBoardReq;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //필드 주입  ~~ 생성자 주입 ..
public class BoardService {

    private final BoardRepository boardRepository;

    @Value("${SECRET_KEY}")
    private String SECRET_KEY;


    //복호화
    public String getSubject(String token) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    @Transactional
    public ResponseEntity<?> findAllArticles() {
        try {
            List<Board> boards = boardRepository.findAllArticles();

            List<BoardListRes> boardListRes = boards.stream()
                    .map(board -> BoardListRes.builder()
                            .id(board.getId())
                            .boardWriter(board.getBoardWriter())
                            .boardTitle(board.getBoardTitle())
                            .boardContents(board.getBoardContents())
                            .build())
                    .collect(Collectors.toList());

            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information(boardListRes)
                    .build();

            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            // 에러 처리
            ApiResponse errorResponse = ApiResponse.builder()
                    .check(false)
                    .build();
            return ResponseEntity.ok(errorResponse);
        }
    }

    @Transactional
    public ResponseEntity<?> save(SaveBoardReq saveBoardReq, HttpServletRequest request) {
        String authorization = getSubject(request.getHeader("Authorization"));


        Board board = Board.builder()
                .boardWriter(authorization)
                .boardTitle(saveBoardReq.getBoardTitle())
                .boardContents(saveBoardReq.getBoardContents())
                .build();

        boardRepository.save(board);

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(Message.builder().message("게시글이 생성되었습니다.").build())
                .build();


        return ResponseEntity.ok(apiResponse);
    }


    @Transactional
    public ResponseEntity<?> update(Long id, UpdateBoardReq updateBoardReq) {

        Optional<Board> board = boardRepository.findById(id);
        if (board.isPresent()) {
            Board updateBoard = board.get();

            updateBoard.updateContents(updateBoardReq.getBoardContents());

            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information(Message.builder().message("수정이 완료되었습니다.").build())
                    .build();
            return ResponseEntity.ok(apiResponse);
        } else {
            ApiResponse errorResponse = ApiResponse.builder()
                    .check(false)
                    .information(Message.builder().message("수정에 실패했습니다.").build())
                    .build();
            return ResponseEntity.ok(errorResponse);
        }

    }

    @Transactional
    public ResponseEntity<?> delete(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if (board.isPresent()) {
            boardRepository.deleteById(id);

            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information(Message.builder().message("삭제가 완료되었습니다.").build())
                    .build();
            return ResponseEntity.ok(apiResponse);
        } else {
            ApiResponse errorResponse = ApiResponse.builder()
                    .check(false)
                    .information(Message.builder().message("삭제할 수 없는 id값 입니다.").build())
                    .build();
            return ResponseEntity.ok(errorResponse);
        }

    }

//    public ResponseEntity<?> findByBoardWriter(String boardWriter) {
//        Optional<Board> board = boardRepository.findByBoardWriter(boardWriter);
//
//        ApiResponse apiResponse = ApiResponse.builder()
//                .check(true)
//                .information(Message.builder().message("찾기가 완료되었습니다.").build())
//                .build();
//        return ResponseEntity.ok(apiResponse);
//    }
}
