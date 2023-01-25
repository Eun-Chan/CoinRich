package com.ddigrang.coinrich.freeboard.domain.repository;

import com.ddigrang.coinrich.freeboard.domain.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {

}

