package com.example.demo.src.board.domain.repository;

import com.example.demo.src.board.domain.Board;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    //select m from Member m where m.nickname = :nickname
    @Query("select b from Board b ORDER BY b.id DESC")
    List<Board> findAllArticles();

//    @Modifying
//    @Query(value = "select * from board_table b where b.boardWriter = :boardWriter")
//    Optional<Board> findByBoardWriter(@Param("boardWriter") String boardWriter);
}
