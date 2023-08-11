package com.example.demo.src.board.domain;

import com.example.demo.src.common.BaseEntity;
import com.example.demo.src.user2.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "board_table")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(length = 20, nullable = false) //크기 20
    private String boardWriter;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @ManyToOne(fetch = FetchType.LAZY) //지연로딩 : 전체 게시글을 조회할 때 유용하게 사용된다.(n+1문제)
    @JoinColumn(name = "userId")
    @BatchSize(size = 10) //게시판을 조회할 때 member 컬렉션에 대해 몇개를 가져올지 설정하는 것
    private Member member;

    @Builder
    public Board(Long id, String boardWriter, String boardTitle, String boardContents, Member member) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.member = member;
    }




    public void updateContents(String boardContents) {
        this.boardContents = boardContents;
    }
}
