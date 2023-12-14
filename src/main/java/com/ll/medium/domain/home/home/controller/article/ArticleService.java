package com.ll.medium.domain.home.home.controller.article;

import com.ll.medium.domain.home.home.controller.DataNotFoundException;
import com.ll.medium.domain.home.home.controller.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Page<Article> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 30, Sort.by(sorts));
        return this.articleRepository.findAll(pageable);
    }

    public Article getArticle(Long id) {
        Optional<Article> article = this.articleRepository.findById(id); //null 값 반환을 위해 Optional 사용
        if (article.isPresent()) { // 값이 있는지 확인
            return article.get();
        } else {
            throw new DataNotFoundException("article not found"); //존재 하지 않음
        }
    }

    public Page<Article> findByUsername(String username, int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 30,Sort.by(sorts));
        return this.articleRepository.findByAuthor_username(username, pageable);
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
