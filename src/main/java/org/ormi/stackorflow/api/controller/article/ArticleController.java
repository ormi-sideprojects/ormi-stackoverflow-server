package org.ormi.stackorflow.api.controller.article;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ormi.stackorflow.core.domain.article.ArticleService;
import org.ormi.stackorflow.core.domain.article.Comment;
import org.ormi.stackorflow.core.domain.common.auth.Provider;
import org.ormi.stackorflow.infra.common.Responses;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public Responses<List<ArticleHomeThreadItemResponse>> findAll(Provider provider) {
    List<ArticleHomeThreadItemResponse> result = service.findAll(provider).stream()
        .map(ArticleHomeThreadItemResponse::of).toList();
    return Responses.ok("조회에 성공했습니다.", result);
  }

  @PostMapping
  public Responses<Void> post(Provider provider, @RequestBody CreateArticleRequest request) {
    service.createArticle(request.toDomain(provider));
    return Responses.created("포스팅에 성공했습니다.");
  }

  @DeleteMapping("{articleId}")
  public Responses<Void> delete(Provider provider, @PathVariable("articleId") long id) {
    service.delete(provider, id);
    return Responses.ok("게시글 삭제에 성공했습니다.");
  }

  @GetMapping("{articleId}/comments")
  public Responses<List<ArticleCommentListItemResponse>> findArticleComment(@PathVariable("articleId") long id) {
    List<Comment> articleComments = service.findArticleComments(id);
    List<ArticleCommentListItemResponse> comments = articleComments.stream()
        .map(ArticleCommentListItemResponse::fromDomain).toList();
    return Responses.ok("조회에 성공했습니다.", comments);
  }

  @PostMapping("{articleId}/comments")
  public Responses<Void> appendComment(Provider provider, @PathVariable("articleId") long id,
      @RequestBody CreateCommentRequest request) {
    service.comment(request.toDomain(provider, id));
    return Responses.created("댓글 작성에 성공했습니다.");
  }

  @PostMapping("{articleId}/me-toos")
  public Responses<Void> addMeToo(Provider provider, @PathVariable("articleId") long id) {
    service.addMeToo(provider, id);
    return Responses.created("나도 궁금해요가 추가되었습니다.");
  }

  @DeleteMapping("{articleId}/me-toos")
  public Responses<Void> deleteMeToo(Provider provider, @PathVariable("articleId") long id) {
    service.deleteMeToo(provider, id);
    return Responses.ok("나도 궁금해요가 제거되었습니다.");
  }
}
