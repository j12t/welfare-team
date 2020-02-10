package io.welfareteam.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.welfareteam.api.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
