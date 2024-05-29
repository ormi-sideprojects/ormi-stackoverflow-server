package org.ormi.stackorflow.domain.article;

import java.util.List;

public interface ArticleRepository {
  List<Article> findAll();

}
