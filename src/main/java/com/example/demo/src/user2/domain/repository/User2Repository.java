package com.example.demo.src.user2.domain.repository;

import com.example.demo.src.user2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface User2Repository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    @Query("select m from Member m where m.nickname = :nickname")
    Optional<Member> findUserWhichSupportingTeam(@Param("nickname") String nickname);
}
