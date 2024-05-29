package org.ormi.stackorflow.api;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ormi.stackorflow.domain.article.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/articles")
public class ArticleController {

  private final ArticleService articleService;

  @GetMapping
  public List<ArticleListDto> findAllForMainList() {
    return articleService.findAllForMainList().stream().map(ArticleListDto::fromDomain).toList();
  }
}
