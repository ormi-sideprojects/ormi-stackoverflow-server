package org.ormi.stackorflow.api.controller.article;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ormi.stackorflow.core.domain.article.ArticleService;
import org.ormi.stackorflow.core.domain.common.Provider;
import org.ormi.stackorflow.infra.common.Responses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {
  private final ArticleService service;

  @GetMapping
  public Responses<List<ArticleHomeThreadItemResponse>> findAll() {
    List<ArticleHomeThreadItemResponse> result = service.findAll().stream()
        .map(ArticleHomeThreadItemResponse::of).toList();
    return Responses.ok("조회에 성공했습니다.",result);
  }

  @PostMapping
  public Responses<Void> post(Provider provider, @RequestBody CreateArticleRequest article) {
    service.createArticle(article.toDomain(provider));
    return Responses.created("포스팅에 성공했습니다.");
  }
}
