package org.ormi.stackorflow.domain.article;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArticleService {
  private final ArticleRepository repository;
  public List<Article> findAllForMainList() {
    return repository.findAll();
  }
}
