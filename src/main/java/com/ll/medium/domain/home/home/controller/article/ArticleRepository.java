package com.ll.medium.domain.home.home.controller.article;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByAuthor_username(String username);
}
