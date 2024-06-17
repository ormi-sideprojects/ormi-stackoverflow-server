package org.ormi.stackorflow.core.domain.article;

import com.querydsl.core.annotations.QueryProjection;

public record ArticleMeToo(int number, boolean isChecked) {
  @QueryProjection
  public ArticleMeToo{}
}
