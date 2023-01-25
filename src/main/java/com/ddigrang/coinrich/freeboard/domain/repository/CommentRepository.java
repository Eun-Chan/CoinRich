package com.ddigrang.coinrich.freeboard.domain.repository;

import com.ddigrang.coinrich.freeboard.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
