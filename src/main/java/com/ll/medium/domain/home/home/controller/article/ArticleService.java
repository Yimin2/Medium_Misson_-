package com.ll.medium.domain.home.home.controller.article;

import com.ll.medium.domain.home.home.controller.DataNotFoundException;
import com.ll.medium.domain.home.home.controller.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getList() {
        return this.articleRepository.findAll();
    }

    public Article getArticle(Long id) {
        Optional<Article> article = this.articleRepository.findById(id); //null 값 반환을 위해 Optional 사용
        if (article.isPresent()) { // 값이 있는지 확인
            return article.get();
        } else {
            throw new DataNotFoundException("article not found"); //존재 하지 않음
        }
    }

    public List<Article> findByUsername(String username) {
        return this.articleRepository.findByAuthor_username(username);
    }

    public void write(String title, String body, Member member) {
        Article article = new Article();
        article.setTitle(title);
        article.setBody(body);
        article.setAuthor(member);
        article.setCreateDate(LocalDateTime.now());
        this.articleRepository.save(article);
    }

    public void modify(Article article, String title, String body) {
        article.setTitle(title);
        article.setBody(body);
        article.setModifyDate(LocalDateTime.now());
        this.articleRepository.save(article);
    }
    public void delete(Article article) {
        this.articleRepository.delete(article);
    }
}
