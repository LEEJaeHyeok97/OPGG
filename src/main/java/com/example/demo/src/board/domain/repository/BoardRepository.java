package com.example.demo.src.board.domain.repository;

import com.example.demo.src.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

//    @Modifying
//    @Query(value = "select * from board_table b where b.boardWriter = :boardWriter")
//    Optional<Board> findByBoardWriter(@Param("boardWriter") String boardWriter);
}
