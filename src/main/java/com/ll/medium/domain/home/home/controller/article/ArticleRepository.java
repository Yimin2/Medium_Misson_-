package com.ll.medium.domain.home.home.controller.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> { // article 엔티티 데이터 접근 및 조작
    Page<Article> findByAuthor_username(String username, Pageable pageable); // author의 username필드가 특정 값과 일치하는 모든 객체를 찾음
    Page<Article> findAll(Pageable pageable);
}
