package com.jihoon.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jihoon.board.entity.BoardViewEntity;

@Repository
public interface BoardViewRepository extends JpaRepository<BoardViewEntity, Integer> {
  List<BoardViewEntity> findTop3ByOrderByFavoriteCountDesc();
}
