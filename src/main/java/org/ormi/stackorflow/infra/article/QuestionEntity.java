package org.ormi.stackorflow.infra.article;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
class QuestionEntity extends ArticleContentEntity {
  @NotNull
  @Column(length = 8, updatable = false)
  private String password;

}
