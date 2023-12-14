package com.ll.medium.domain.home.home.controller.article;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> { // article 엔티티 데이터 접근 및 조작
    List<Article> findByAuthor_username(String username); // author의 username필드가 특정 값과 일치하는 모든 객체를 찾음
}
