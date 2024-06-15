package org.ormi.stackorflow.core.domain.article;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
  private final ArticleRepository repository;
  public void createArticle(CreateArticle article) {
    repository.create(article);
  }

  public List<Article> findAll() {
    return repository.findAll();
  }
}
